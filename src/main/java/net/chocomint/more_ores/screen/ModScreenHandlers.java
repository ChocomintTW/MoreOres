package net.chocomint.more_ores.screen;

import net.chocomint.more_ores.More_Ores;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
	public static ScreenHandlerType<AlloyManufactoryScreenHandler> ALLOY_MANUFACTORY_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(More_Ores.MOD_ID, "alloy_manufactory"), AlloyManufactoryScreenHandler::new);
	public static ScreenHandlerType<FillerScreenHandler> FILLER_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(More_Ores.MOD_ID, "filler"), FillerScreenHandler::new);
	public static ScreenHandlerType<ATMScreenHandler> ATM_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(More_Ores.MOD_ID, "atm"), ATMScreenHandler::new);
}
