package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.config.ModConfigs;
import net.chocomint.more_ores.dynamic_recipe.ModDynamicRecipes;
import net.chocomint.more_ores.effect.ModEffects;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.potion.ModPotions;
import net.chocomint.more_ores.recipe.ModRecipes;
import net.chocomint.more_ores.util.ModRegistries;
import net.chocomint.more_ores.world.gen.ModWorldGen;
import net.chocomint.more_ores.world.structures.ModConfiguredStructures;
import net.chocomint.more_ores.world.structures.ModStructures;
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
		ModDynamicRecipes.registerModRecipes();
		ModRecipes.register();

		ModRegistries.registerModFuel();
		ModRegistries.registerCommands();
		ModRegistries.registerEvents();

		// Potions
		ModPotions.registerModPotions();
		ModEffects.registerModEffect();

		// Generates
		ModWorldGen.generateModWorldGen();

		// Structure
		ModStructures.setupAndRegisterStructureFeatures();
		ModConfiguredStructures.registerConfiguredStructures();
		ModStructures.addStructureSpawningToDimensionsAndBiomes();

		// Rendering
		ModBlocks.renderGlassBlock(ModBlocks.TOUGHENED_GLASS);

		System.out.println("Hello Fabric world!");
	}
}
