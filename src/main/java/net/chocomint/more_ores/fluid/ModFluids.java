package net.chocomint.more_ores.fluid;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModFluids {
	public static final FlowableFluid RADIOACTIVE_WATER_STILL = registerFluid("radioactive_water_still", new RadioactiveWaterFluid.Still());
	public static final FlowableFluid RADIOACTIVE_WATER_FLOWING = registerFluid("radioactive_water_flowing", new RadioactiveWaterFluid.Flowing());

	private static FlowableFluid registerFluid(String name, FlowableFluid fluid) {
		return Registry.register(Registry.FLUID, new Identifier(More_Ores.MOD_ID, name), fluid);
	}
}
