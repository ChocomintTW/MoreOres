package net.chocomint.more_ores.registries;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {

	public static void registerModFuel() {
		System.out.println("Registering Fuels for " + More_Ores.MOD_ID);
		FuelRegistry registry = FuelRegistry.INSTANCE;

		registry.add(ModItems.ADVANCED_COAL, 12000);
	}

}
