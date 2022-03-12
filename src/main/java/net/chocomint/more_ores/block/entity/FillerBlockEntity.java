package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.item.custom.ContainerItem;
import net.chocomint.more_ores.item.inventory.ImplementedInventory;
import net.chocomint.more_ores.screen.FillerScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;

public class FillerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

	protected final PropertyDelegate propertyDelegate;
	private int lava = 0;

	public FillerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.FILLER_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				return switch (index) {
					case 0 -> FillerBlockEntity.this.lava;
					default -> 0;
				};
			}

			public void set(int index, int value) {
				switch (index) {
					case 0 -> FillerBlockEntity.this.lava = value;
				}
			}

			public int size() {
				return 1;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText("block.more_ores.filler");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new FillerScreenHandler(syncId, inv, this, this.propertyDelegate);
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

	public static void tick(World world, BlockPos pos, BlockState state, FillerBlockEntity entity) {
		if(entity.inventory.get(0).getItem() == ModItems.LAVA_TANK) {

			ItemStack item = entity.inventory.get(0);

			if (entity.getLava() > 0 && ContainerItem.getFluid(item) < 19000) {
				entity.setLava(entity.getLava() - 10);
				NbtCompound nbt = item.getOrCreateNbt();
				nbt.putInt("fluid", ContainerItem.getFluid(item) + 10);
				item.setNbt(nbt);
			}

		}
	}

	public int getLava() {
		return this.propertyDelegate.get(0);
	}
	public void setLava(int value) {
		this.propertyDelegate.set(0, value);
	}
}
