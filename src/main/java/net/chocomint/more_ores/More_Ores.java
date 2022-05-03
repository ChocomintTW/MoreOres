package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.chocomint.more_ores.config.ModConfigs;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.recipe.ModRecipes;
import net.chocomint.more_ores.util.ModLootTableModifiers;
import net.chocomint.more_ores.util.ModRegistries;
import net.chocomint.more_ores.util.atm.ATMCostJsonSerializer;
import net.chocomint.more_ores.util.effect.ModEffects;
import net.chocomint.more_ores.util.enchantment.ModEnchantments;
import net.chocomint.more_ores.util.potion.ModPotions;
import net.chocomint.more_ores.world.biome.ModBiomes;
import net.chocomint.more_ores.world.dimension.ModDimensions;
import net.chocomint.more_ores.world.feature.ModConfiguredFeatures;
import net.chocomint.more_ores.world.feature.ModFeatures;
import net.chocomint.more_ores.world.gen.ModWorldGen;
import net.chocomint.more_ores.world.structures.ModStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.math.Vec3d;

import java.io.IOException;

import static net.chocomint.more_ores.item.custom.GravityWandItem.*;

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
		ModLootTableModifiers.modifyLootTables();
		ModEnchantments.registerModEnchantments();

		ModRegistries.registerAllRegistries();

		// Potions
		ModPotions.registerModPotions();
		ModEffects.registerModEffect();

		// Rendering
		ModBlocks.renderGlassBlock(ModBlocks.TOUGHENED_GLASS);

		// read
		try {
			ATMCostJsonSerializer.init();
			ATMCostJsonSerializer.ReadJsonIntoList();
		} catch (IOException e) { e.printStackTrace(); }

//		ServerTickEvents.START_WORLD_TICK.register(serverWorld -> {
//			if (FBE != null && PLAYER != null) {
//				if (ON_USE) {
//					Vec3d pos = Vec3d.fromPolar(PLAYER.getPitch(), PLAYER.getYaw()).multiply(DISTANCE).add(PLAYER.getEyePos());
//					FBE.setNoGravity(true);
//					FBE.setPos(pos.getX(), pos.getY(), pos.getZ());
//				}
//			}
//		});

		System.out.println("Hello Fabric World!");
	}
}
