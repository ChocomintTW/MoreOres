package net.chocomint.more_ores.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ContainerItem extends Item {

	public ContainerItem(Settings settings, int fluid_max) {
		super(settings.maxCount(1));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText(getFluid(stack) + ""));
	}

	public static int getFluid(ItemStack stack) {
		return stack.getOrCreateNbt().getInt("fluid");
	}

	public static int getFluidMax(ItemStack stack) {
		return 20000;
	}

	public boolean isItemBarVisible(ItemStack stack) {
		return true;
	}

	public int getItemBarStep(ItemStack stack) {
		return Math.round((float)getFluid(stack) / (float)getFluidMax(stack) * (float)12);
	}

	public int getItemBarColor(ItemStack stack) {
		return MathHelper.packRgb(0.082F, 1.0F, 0.169F);
	}
}
