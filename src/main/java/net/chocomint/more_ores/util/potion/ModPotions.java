package net.chocomint.more_ores.util.potion;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.util.effect.ModEffects;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {

	public static Potion STRENGTHEN_FIRE_RESISTANCE_POTION = registerPotion("strengthen_fire_resistance",
			new PotionRecipe(Potions.LONG_FIRE_RESISTANCE, ModItems.GLOW_BLAZE_ALLOY),
			new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, minute(12), 0),
			new StatusEffectInstance(StatusEffects.RESISTANCE, minute(12), 2));

	public static Potion FREEZE_POTION = registerPotion("freeze", new PotionRecipe(Potions.SLOWNESS, Items.BLUE_ICE),
			new StatusEffectInstance(ModEffects.FREEZE, second(40), 1));


	private static Potion registerPotion(String name, PotionRecipe potionRecipe, StatusEffectInstance... sei) {
		Potion p = Registry.register(Registry.POTION, new Identifier(More_Ores.MOD_ID, name), new Potion(sei));
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(potionRecipe.getPotion(), potionRecipe.getItem(), p);
		return p;
	}

	public static void registerModPotions() {
		System.out.println("Registering Mod Potions for " + More_Ores.MOD_ID);
	}

	private static int minute(double min) {
		return (int)Math.round(min * 60 * 20);
	}

	private static int second(double sec) {
		return (int)Math.round(sec * 20);
	}

	private record PotionRecipe(Potion potion, Item item) {

		public Potion getPotion() {
			return potion;
		}

		public Item getItem() {
			return item;
		}
	}
}
