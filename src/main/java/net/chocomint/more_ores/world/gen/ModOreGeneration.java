package net.chocomint.more_ores.world.gen;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.world.feature.ModConfiguredFeatures;
import net.chocomint.more_ores.world.feature.ModPlacedFeatures;
import net.chocomint.more_ores.world.feature.ore.ModOreFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ModOreGeneration {

	public static void generateOres() {
		// Ores in Overworld
		generator("lead_ore", new OreGenerationReference(
				getOverworldList(ModBlocks.LEAD_ORE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				8, 7,
				HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))
		));
		generator("silver_ore", new OreGenerationReference(
				getOverworldList(ModBlocks.SILVER_ORE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				5, 5,
				HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))
		));
		generator("titanium_ore", new OreGenerationReference(
				getOverworldList(ModBlocks.SILVER_ORE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				8, 8,
				HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))
		));

		// Ores in Nether
		generator("charged_quartz_ore", new OreGenerationReference(
				List.of(new Pair<>(ModBlocks.CHARGED_QUARTZ_ORE, OreConfiguredFeatures.NETHERRACK)),
				BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
				6, 4,
				HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.aboveBottom(64))
		));

		// Stones
		generator("marble", new OreGenerationReference(
				getOverworldList(ModBlocks.MARBLE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				50, 5, huAll
		));
		generator("serpentine", new OreGenerationReference(
				getOverworldList(ModBlocks.SERPENTINE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				40, 5, huAll
		));
	}

	public record OreGenerationReference(List<Pair<Block, RuleTest>> BRP_list, Predicate<BiomeSelectionContext> biomeSelection,
	                                      GenerationStep.Feature step, int veinSize, int veinsPerChunk,
	                                     HeightRangePlacementModifier modifier) {}

	public static void generator(String name, OreGenerationReference ref) {
		List<OreFeatureConfig.Target> list = new ArrayList<>();
		for (Pair<Block, RuleTest> p : ref.BRP_list)
			list.add(OreFeatureConfig.createTarget(p.getRight(), p.getLeft().getDefaultState()));

		RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE =
				ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(list, ref.veinSize));

		RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name + "_placed", ORE,
				ModOreFeatures.modifiersWithCount(ref.veinsPerChunk, ref.modifier));

		BiomeModifications.addFeature(ref.biomeSelection, ref.step, ORE_PLACED.getKey().get());
	}
	// HeightRangePlacementModifier 有很多不同種的生成分布方式。像 trapezoid 就是梯形分布，上下最少，中間最多

	// utilities
	private static List<Pair<Block, RuleTest>> getOverworldList(Block block) {
		return List.of(new Pair<>(block, OreConfiguredFeatures.STONE_ORE_REPLACEABLES),
				new Pair<>(block, OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES));
	}
	private static final HeightRangePlacementModifier huAll = HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP);
}
