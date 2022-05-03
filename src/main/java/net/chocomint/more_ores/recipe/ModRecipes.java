package net.chocomint.more_ores.recipe;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
	public static AlloyManufactoryRecipe.Type ALLOY_MANUFACTORY_RECIPE_TYPE;

	public static void register() {
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(More_Ores.MOD_ID, AlloyManufactoryRecipe.Serializer.ID),
				AlloyManufactoryRecipe.Serializer.INSTANCE);

		ALLOY_MANUFACTORY_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE,
				new Identifier(More_Ores.MOD_ID, AlloyManufactoryRecipe.Type.ID), AlloyManufactoryRecipe.Type.INSTANCE);
	}
}
