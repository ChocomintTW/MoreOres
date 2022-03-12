package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.config.ModConfigs;
import net.chocomint.more_ores.recipe.dynamic.ModDynamicRecipes;
import net.chocomint.more_ores.util.effect.ModEffects;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.util.potion.ModPotions;
import net.chocomint.more_ores.recipe.ModRecipes;
import net.chocomint.more_ores.util.ModRegistries;
import net.chocomint.more_ores.world.gen.ModWorldGen;
import net.chocomint.more_ores.world.structures.ModConfiguredStructures;
import net.chocomint.more_ores.world.structures.ModStructures;
import net.fabricmc.api.ModInitializer;

//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//   ..............................................
//              佛祖保佑            永無BUG

public class More_Ores implements ModInitializer {

	public static final String MOD_ID = "more_ores";

	@Override
	public void onInitialize() {
		// Configs
		ModConfigs.registerConfigs();

		// Registries
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
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
