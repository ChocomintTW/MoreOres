package net.chocomint.more_ores.world.dimension;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;

public class ModDimensions {
	public static final RegistryKey<DimensionOptions> CAVE_DIMENSION = RegistryKey.of(Registry.DIMENSION_KEY,
			new Identifier(More_Ores.MOD_ID, "cave"));
	public static RegistryKey<World> CAVE_KEY = RegistryKey.of(Registry.WORLD_KEY, CAVE_DIMENSION.getValue());

	public static void registerModDimensions() {
		System.out.println("Registering Mod Dimensions for " + More_Ores.MOD_ID);
	}
}
