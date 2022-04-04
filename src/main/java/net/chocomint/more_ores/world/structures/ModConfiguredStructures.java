package net.chocomint.more_ores.world.structures;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ModConfiguredStructures {

//	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_ALTAR = register("configured_altar", ModStructures.ALTAR
//			.configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0)));

	private static ConfiguredStructureFeature<?, ?> register(String name, ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE,
				new Identifier(More_Ores.MOD_ID, name), configuredStructureFeature);
	}

	public static void registerConfiguredStructures() {
		System.out.println("Registering Mod Configured Structure for " + More_Ores.MOD_ID);
	}
}
