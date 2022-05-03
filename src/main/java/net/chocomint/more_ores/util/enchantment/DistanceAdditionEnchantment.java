package net.chocomint.more_ores.util.enchantment;

import net.chocomint.more_ores.item.custom.TeleportWandItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class DistanceAdditionEnchantment extends Enchantment {
	public DistanceAdditionEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
		super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof TeleportWandItem;
	}
}
