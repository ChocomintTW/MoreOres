package net.chocomint.more_ores.util.enchantment;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

	public static final Enchantment DISTANCE_ADDITION = register("distance_addition",
			new DistanceAdditionEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));

	private static Enchantment register(String name, Enchantment enchantment) {
		return Registry.register(Registry.ENCHANTMENT, new Identifier(More_Ores.MOD_ID, name), enchantment);
	}

	public static void registerModEnchantments() {
		System.out.println("Register Mod Enchantments");
	}
}
