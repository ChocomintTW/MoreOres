package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.config.ModConfigs;
import net.chocomint.more_ores.dynamic_recipe.ModDynamicRecipes;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.recipe.ModRecipes;
import net.chocomint.more_ores.registries.ModRegistries;
import net.chocomint.more_ores.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

public class More_Ores implements ModInitializer {

	public static final String MOD_ID = "more_ores";

	@Override
	public void onInitialize() {
		// Configs
		ModConfigs.registerConfigs();

		// Registries
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModRegistries.registerModFuel();
		ModDynamicRecipes.registerModRecipes();
		ModRecipes.register();

		// Generates
		ModWorldGen.generateModWorldGen();

		// Rendering
		ModBlocks.renderGlassBlock(ModBlocks.TOUGHENED_GLASS);

		System.out.println("Hello Fabric world!");
	}
}
