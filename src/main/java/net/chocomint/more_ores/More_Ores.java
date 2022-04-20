package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.chocomint.more_ores.config.ModConfigs;
import net.chocomint.more_ores.util.atm.ATMCostJsonSerializer;
import net.chocomint.more_ores.util.effect.ModEffects;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.util.potion.ModPotions;
import net.chocomint.more_ores.recipe.ModRecipes;
import net.chocomint.more_ores.util.ModRegistries;
import net.chocomint.more_ores.world.biome.ModBiomes;
import net.chocomint.more_ores.world.dimension.ModDimensions;
import net.chocomint.more_ores.world.feature.ModConfiguredFeatures;
import net.chocomint.more_ores.world.feature.ModFeatures;
import net.chocomint.more_ores.world.gen.ModWorldGen;
import net.chocomint.more_ores.world.structures.ModStructures;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;

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

		// Generates
		ModWorldGen.generateModWorldGen();
		ModDimensions.registerModDimensions();
		ModFeatures.registerModFeatures();
		ModConfiguredFeatures.registerModConfiguredFeatures();
		ModBiomes.registerModBiomes();

		// Structure
		ModStructures.registerStructureFeatures();

		// Registries
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerAllBlockEntities();
		ModRecipes.register();

		ModRegistries.registerModFuel();
		ModRegistries.registerCommands();
		ModRegistries.registerEvents();
		ModRegistries.registerCustomTrades();
		ModRegistries.registerStrippableBlocks();
		ModRegistries.registerFlammableBlocks();

		// Potions
		ModPotions.registerModPotions();
		ModEffects.registerModEffect();

		// Rendering
		ModBlocks.renderGlassBlock(ModBlocks.TOUGHENED_GLASS);

		// read
		try {
			ATMCostJsonSerializer.init();
			ATMCostJsonSerializer.ReadJsonIntoList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Hello Fabric World!");
	}
}
