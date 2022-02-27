package net.chocomint.more_ores.world.gen;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.function.Predicate;

public class ModOresGenerate {

	// Main generate function
	public static void generateModOres() {
		// Ores in Overworld
		generator("silver_ore", ModBlocks.SILVER_ORE,
				new OreGenerationReference(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BiomeSelectors.foundInOverworld(),
						5, 5, height.BOTTOM, 64));
		generator("titanium_ore", ModBlocks.TITANIUM_ORE,
				new OreGenerationReference(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BiomeSelectors.foundInOverworld(),
						8, 8, height.BOTTOM, 64));

		// Ores in Nether
		generator("charged_quartz_ore", ModBlocks.CHARGED_QUARTZ_ORE,
				new OreGenerationReference(OreConfiguredFeatures.NETHERRACK, BiomeSelectors.foundInTheNether(),
						6, 4, 0, 64));

		// Stones
		generator("marble", ModBlocks.MARBLE,
				new OreGenerationReference(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BiomeSelectors.foundInOverworld(),
						50, 5, height.BOTTOM, height.TOP));
		generator("serpentine", ModBlocks.SERPENTINE,
				new OreGenerationReference(OreConfiguredFeatures.BASE_STONE_OVERWORLD, BiomeSelectors.foundInOverworld(),
						40, 5, height.BOTTOM, height.TOP));
	}

	private static class OreGenerationReference {
		RuleTest rule;
		Predicate<BiomeSelectionContext> biomeSelection;
		int veinSize, veinsPerChunk, heightBottom, heightTop;

		// constructor
		public OreGenerationReference(RuleTest r, Predicate<BiomeSelectionContext> b,
		                              int Vein_Size, int Veins_Per_Chunk, int h_bottom, int h_top) {
			rule           = r;
			biomeSelection = b;
			veinSize       = Vein_Size;
			veinsPerChunk  = Veins_Per_Chunk;
			heightBottom   = h_bottom;
			heightTop      = h_top;
		}
	}
	private static class height {
		public static final int TOP = 319, BOTTOM = -64;
	}

	public static void generator(String name, Block ore, OreGenerationReference ref) {
		Registry_and_GenerateOres(
				name, Feature.ORE.configure(new OreFeatureConfig(ref.rule, ore.getDefaultState(), ref.veinSize)),
				ref.biomeSelection,
				CountPlacementModifier.of(ref.veinsPerChunk),
				SquarePlacementModifier.of(), // spreading horizontally
				HeightRangePlacementModifier.uniform(YOffset.aboveBottom(ref.heightBottom), YOffset.belowTop(ref.heightTop)));
	}

	public static void Registry_and_GenerateOres(String name, ConfiguredFeature<?, ?> configuredFeature,
	                                             Predicate<BiomeSelectionContext> biomeSelection, PlacementModifier... modifiers) {
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(More_Ores.MOD_ID, name), configuredFeature);

		Registry.register(BuiltinRegistries.PLACED_FEATURE,
				new Identifier(More_Ores.MOD_ID, name), configuredFeature.withPlacement(List.of(modifiers)));

		BiomeModifications.addFeature(biomeSelection, GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(More_Ores.MOD_ID, name)));
	}

}
