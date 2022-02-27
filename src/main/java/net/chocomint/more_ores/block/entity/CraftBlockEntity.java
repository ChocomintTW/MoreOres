package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.item.inventory.ImplementedInventory;
import net.chocomint.more_ores.recipe.CraftBlockRecipe;
import net.chocomint.more_ores.screen.CraftBlockScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CraftBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	// How many ticks it will take to craft the item (divide by twenty to get seconds count)
	// In our case this should be even divisible by 21 as that's our pixel count for our progress arrow
	private int maxProgress = 63;

	public CraftBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.CRAFT_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				return switch (index) {
					case 0 -> CraftBlockEntity.this.progress;
					case 1 -> CraftBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			public void set(int index, int value) {
				switch (index) {
					case 0 -> CraftBlockEntity.this.progress = value;
					case 1 -> CraftBlockEntity.this.maxProgress = value;
				}
			}

			public int size() {
				return 2;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new LiteralText("Craft Block");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new CraftBlockScreenHandler(syncId, inv, this, this.propertyDelegate);
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

	public static void tick(World world, BlockPos pos, BlockState state, CraftBlockEntity entity) {
		if(hasRecipe(entity)) {
			entity.progress++;
			if(entity.progress > entity.maxProgress) {
				craftItem(entity);
			}
		} else {
			entity.resetProgress();
		}
	}

	private static boolean hasRecipe(CraftBlockEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<CraftBlockRecipe> match = world.getRecipeManager()
				.getFirstMatch(CraftBlockRecipe.Type.INSTANCE, inventory, world);

		return match.isPresent() && evaluateWeather(match.get().getWeather(), world)
				&& canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
	}

	private static void craftItem(CraftBlockEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<CraftBlockRecipe> match = world.getRecipeManager()
				.getFirstMatch(CraftBlockRecipe.Type.INSTANCE, inventory, world);

		if(match.isPresent()) {
			entity.removeStack(0,1);
			entity.removeStack(1,1);
			entity.setStack(2, new ItemStack(match.get().getOutput().getItem(),
					entity.getStack(2).getCount() + 1));

			if(!world.isClient() && match.get().getWeather() == CraftBlockRecipe.Weather.THUNDERING) {
				EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, null, null, entity.pos,
						SpawnReason.TRIGGERED, true, true);
			}

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

	private static boolean evaluateWeather(CraftBlockRecipe.Weather weather, World world) {
		boolean matches = false;

		if(weather == CraftBlockRecipe.Weather.CLEAR && !world.isRaining()) {
			matches = true;
		}

		if(weather == CraftBlockRecipe.Weather.RAIN && world.isRaining()) {
			matches = true;
		}

		if(weather == CraftBlockRecipe.Weather.THUNDERING && world.isThundering()) {
			matches = true;
		}

		return matches;
	}
}
