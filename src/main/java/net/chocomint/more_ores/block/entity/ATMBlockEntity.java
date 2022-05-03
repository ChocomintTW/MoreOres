package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.item.inventory.ImplementedInventory;
import net.chocomint.more_ores.screen.ATMScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.chocomint.more_ores.block.custom.ATMBlock.COST;
import static net.chocomint.more_ores.util.atm.ATMCostJsonSerializer.COST_LIST;

public class ATMBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

	public ATMBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ATM_BLOCK_ENTITY, pos, state);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText("block.more_ores.atm");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new ATMScreenHandler(syncId, inv, this);
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

	public static void tick(World world, BlockPos pos, BlockState state, ATMBlockEntity entity) {
		if (!world.isClient())
		{
			COST = COST_LIST.getOrDefault(ItemToKey(entity.getItems().get(0)), -1);
		}
	}

	private static String ItemToKey(ItemStack item) {
		String[] s = item.getTranslationKey().split("\\.");
		int l = s.length;
		return s[l - 2] + ":" + s[l - 1];
	}
}
