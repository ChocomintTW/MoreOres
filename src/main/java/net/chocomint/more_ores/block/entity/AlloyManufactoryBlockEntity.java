package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.item.inventory.ImplementedInventory;
import net.chocomint.more_ores.recipe.AlloyManufactoryRecipe;
import net.chocomint.more_ores.screen.AlloyManufactoryScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloyManufactoryBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final int amount = 4;             // 格子數
	private final int progressbarLength = 42; // 製作進度條長度
	private final int seconds = 2;            // 製作時間

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(amount, ItemStack.EMPTY);

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	// How many ticks it will take to craft the item (divide by twenty to get seconds count)
	// In our case this should be even divisible by 21 as that's our pixel count for our progress arrow
	private int maxProgress = progressbarLength * seconds;
	private int lava = 0;

	public AlloyManufactoryBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ALLOY_MANUFACTORY_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				return switch (index) {
					case 0 -> AlloyManufactoryBlockEntity.this.progress;
					case 1 -> AlloyManufactoryBlockEntity.this.maxProgress;
					case 2 -> AlloyManufactoryBlockEntity.this.lava;
					default -> 0;
				};
			}

			public void set(int index, int value) {
				switch (index) {
					case 0 -> AlloyManufactoryBlockEntity.this.progress = value;
					case 1 -> AlloyManufactoryBlockEntity.this.maxProgress = value;
					case 2 -> AlloyManufactoryBlockEntity.this.lava = value;
				}
			}

			public int size() {
				return 3;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new LiteralText("Alloy Manufactory");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new AlloyManufactoryScreenHandler(syncId, inv, this, this.propertyDelegate);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, this.inventory);
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
	}

	public static void tick(World world, BlockPos pos, BlockState state, AlloyManufactoryBlockEntity entity) {
		if(hasRecipe(entity) && entity.getLava() > 0) {
			entity.progress++;
			if(entity.progress > entity.maxProgress) {
				craftItem(entity);
			}
		} else {
			entity.resetProgress();
		}
	}

	private static boolean hasRecipe(AlloyManufactoryBlockEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<AlloyManufactoryRecipe> match = world.getRecipeManager()
				.getFirstMatch(AlloyManufactoryRecipe.Type.INSTANCE, inventory, world);

		return match.isPresent() && (entity.getLava() >= match.get().getLavaAmount())
				&& canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
	}

	private static void craftItem(AlloyManufactoryBlockEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<AlloyManufactoryRecipe> match = world.getRecipeManager()
				.getFirstMatch(AlloyManufactoryRecipe.Type.INSTANCE, inventory, world);

		if(match.isPresent()) {
			// how to get the amount of the item in ingredients?
			entity.removeStack(0, 1);
			entity.removeStack(1, 1);
			entity.setLava(entity.getLava() - match.get().getLavaAmount());
			entity.setStack(2, new ItemStack(match.get().getOutput().getItem(),
					entity.getStack(2).getCount() + 1));

			entity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
		return inventory.getStack(2).getItem() == output.getItem() || inventory.getStack(2).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
		return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
	}

	public int getLava() {
		return this.propertyDelegate.get(2);
	}
	public void setLava(int value) {
		this.propertyDelegate.set(2, value);
	}
}
