//package net.chocomint.more_ores.world.structures;
//
//import net.chocomint.more_ores.More_Ores;
//import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
//import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.registry.BuiltinRegistries;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.util.registry.RegistryKey;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
//import net.minecraft.world.gen.feature.StructureFeature;
//import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
//
//public class ModStructures {
//
//	public static StructureFeature<StructurePoolFeatureConfig> ALTAR = new AltarStructure(StructurePoolFeatureConfig.CODEC);
//
//	public static void setupAndRegisterStructureFeatures() {
//		registerSurfaceStructure("altar", ALTAR, new StructureConfig(50, 20, 601684253));
//	}
//
//	public static void addStructureSpawningToDimensionsAndBiomes() {
//
//		biomeAdder(ModConfiguredStructures.CONFIGURED_ALTAR,
//				Biome.Category.DESERT,
//				Biome.Category.EXTREME_HILLS,
//				Biome.Category.FOREST,
//				Biome.Category.JUNGLE,
//				Biome.Category.PLAINS,
//				Biome.Category.SAVANNA,
//				Biome.Category.TAIGA);
//
//	}
//
//	private static void registerSurfaceStructure(String name, StructureFeature<StructurePoolFeatureConfig> sf, StructureConfig sc) {
//		FabricStructureBuilder.create(new Identifier(More_Ores.MOD_ID, name), sf)
//				.step(GenerationStep.Feature.SURFACE_STRUCTURES)
//				.defaultConfig(sc)
//				.adjustsSurface()
//				.register();
//	}
//
//	private static void biomeAdder(ConfiguredStructureFeature<?, ?> csf, Biome.Category... categories) {
//		BiomeModifications.addStructure(
//				BiomeSelectors.categories(categories),
//				RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(csf))
//		);
//	}
//}
