package net.chocomint.more_ores.world.feature;

import net.chocomint.more_ores.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

public class ModFeatures {

	public static final Feature<DefaultFeatureConfig> STONE = register("stone", new StoneFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<DefaultFeatureConfig> SINGLE_END_VIBRANIUM_NYLIUM = register(
			"single_end_vibranium_nylium", new SurfaceSingleBlockFeature(DefaultFeatureConfig.CODEC, ModBlocks.END_AQUAMARINE_NYLIUM));

	private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return Registry.register(Registry.FEATURE, name, feature);
	}

	public static void registerModFeatures() {
		System.out.println("Register mod features");
	}
}
