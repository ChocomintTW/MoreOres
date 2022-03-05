package net.chocomint.more_ores;

import net.chocomint.more_ores.screen.AlloyManufactoryScreen;
import net.chocomint.more_ores.screen.FillerScreen;
import net.chocomint.more_ores.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		System.out.println("Client Mod!");
		ScreenRegistry.register(ModScreenHandlers.ALLOY_MANUFACTORY_SCREEN_HANDLER, AlloyManufactoryScreen::new);
		ScreenRegistry.register(ModScreenHandlers.FILLER_SCREEN_HANDLER, FillerScreen::new);
	}
}
