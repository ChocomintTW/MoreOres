package net.chocomint.more_ores.util;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.command.ReturnPositionCommand;
import net.chocomint.more_ores.command.SetPositionCommand;
import net.chocomint.more_ores.events.PlayerEvents;
import net.chocomint.more_ores.item.ModItems;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {

	public static void registerModFuel() {
		System.out.println("Registering Fuels for " + More_Ores.MOD_ID);
		FuelRegistry registry = FuelRegistry.INSTANCE;

		registry.add(ModItems.ADVANCED_COAL, 12000);
	}

	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(SetPositionCommand::register);
		CommandRegistrationCallback.EVENT.register(ReturnPositionCommand::register);
	}

	public static void registerEvents() {
		ServerPlayerEvents.COPY_FROM.register(new PlayerEvents());
	}
}
