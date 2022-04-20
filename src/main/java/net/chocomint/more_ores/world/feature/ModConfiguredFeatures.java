package net.chocomint.more_ores.world.feature;

import net.chocomint.more_ores.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.function.Predicate;

public class ModConfiguredFeatures {

	public static void registerModConfiguredFeatures() {

		generateFeature("stone", ModFeatures.STONE, DefaultFeatureConfig.DEFAULT,
				BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION,
				0.5F, 0 ,0.05F, 1);

		System.out.println("Register mod configured features");
	}

	public static <FC extends FeatureConfig, F extends Feature<FC>>
	void generateFeature(String name, F feature, FC config, Predicate<BiomeSelectionContext> selector,
	                     GenerationStep.Feature step, float chance, int count, float extraChance, int extraCount){
		RegistryEntry<ConfiguredFeature<FC, ?>> CONFIGURED_ENTRY = ConfiguredFeatures.register(name + "_feature", feature, config);

		RegistryEntry<PlacedFeature> PLACED_CHECK = PlacedFeatures.register(name + "_checked", CONFIGURED_ENTRY,
				PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP);

		RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> SPAWN_ENTRY = ConfiguredFeatures.register(name + "_spawn",
				Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PLACED_CHECK, chance)), PLACED_CHECK));

		RegistryEntry<PlacedFeature> PLACED_ENTRY = PlacedFeatures.register(name + "_placed", SPAWN_ENTRY,
				VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(count, extraChance, extraCount)));

		BiomeModifications.addFeature(selector, step, PLACED_ENTRY.getKey().get());
	}
}
