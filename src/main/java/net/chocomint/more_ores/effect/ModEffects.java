package net.chocomint.more_ores.effect;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {

	public static StatusEffect FREEZE = Registry.register(Registry.STATUS_EFFECT, new Identifier(More_Ores.MOD_ID, "freeze"),
			new FreezeEffect(StatusEffectCategory.HARMFUL, 2998260));

	public static void registerModEffect() {
		System.out.println("Registering Mod Potions for " + More_Ores.MOD_ID);
	}
}
