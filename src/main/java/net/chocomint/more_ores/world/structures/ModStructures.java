package net.chocomint.more_ores.world.structures;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.mixin.StructureFeatureAccessor;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {

	public static StructureFeature<?> ALTAR_STRUCTURE = new AltarStructure();
	public static StructureFeature<?> END_HOUSE_STRUCTURE = new EndHouseStructure();

	public static void registerStructureFeatures() {
		StructureFeatureAccessor.callRegister(More_Ores.MOD_ID + ":altar_structure", ALTAR_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
		StructureFeatureAccessor.callRegister(More_Ores.MOD_ID + ":end_house_structure", END_HOUSE_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
	}
}
