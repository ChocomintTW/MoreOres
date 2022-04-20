package net.chocomint.more_ores.world.biome;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.world.feature.ModFeatures;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.random.ChunkRandom;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class ModBiomes {

	public static final RegistryKey<Biome> END_VIBRANIUM_PLAINS_KEY = registerKey("end_vibranium_plains");
	public static final Biome END_VIBRANIUM_PLAINS = createEndVibraniumPlains();

	public static RegistryKey<Biome> registerKey(String name) {
		return RegistryKey.of(Registry.BIOME_KEY, new Identifier(More_Ores.MOD_ID, name));
	}

	public static Biome createEndVibraniumPlains() {
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addFarmAnimals(spawnSettings);

		GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

//		RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> CONFIGURED_ENTRY = ConfiguredFeatures.register(
//				"end_aquamarine_nylium_feature", ModFeatures.SINGLE_END_VIBRANIUM_NYLIUM, DefaultFeatureConfig.DEFAULT);
//		RegistryEntry<PlacedFeature> PLACED_CHECK = PlacedFeatures.register("end_aquamarine_nylium_checked", CONFIGURED_ENTRY,
//				PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP);
//		generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, PLACED_CHECK);

		DefaultBiomeFeatures.addLandCarvers(generationSettings);

		DefaultBiomeFeatures.addDungeons(generationSettings);
		DefaultBiomeFeatures.addMineables(generationSettings);
		DefaultBiomeFeatures.addDefaultOres(generationSettings);

		DefaultBiomeFeatures.addDefaultDisks(generationSettings);
		DefaultBiomeFeatures.addSprings(generationSettings);

		return new Biome.Builder()
				.precipitation(Biome.Precipitation.NONE)
				.category(Biome.Category.NONE)
				.temperature(0.8F)
				.downfall(0)
				.effects(new BiomeEffects.Builder()
						.skyColor(0)
						.fogColor(8228778)
						.waterColor(4159204)
						.waterFogColor(855385)
						.build())
				.spawnSettings(spawnSettings.build())
				.generationSettings(generationSettings.build())
				.build();
	}

	public static void registerModBiomes() {
		Registry.register(BuiltinRegistries.BIOME, END_VIBRANIUM_PLAINS_KEY.getValue(), END_VIBRANIUM_PLAINS);
		TheEndBiomes.addHighlandsBiome(END_VIBRANIUM_PLAINS_KEY, 1);

//		SurfaceBuilder builder = new SurfaceBuilder(NoiseParametersKeys.SURFACE, ModBlocks.END_AQUAMARINE_NYLIUM.getDefaultState(),
//				0, 66995261, ChunkRandom.RandomProvider.LEGACY);

		System.out.println("Register Mod Biomes");
	}
}
