package net.chocomint.more_ores.screen;

import net.chocomint.more_ores.More_Ores;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
	public static ScreenHandlerType<CraftBlockScreenHandler> CRAFT_BLOCK_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(More_Ores.MOD_ID, "craft_block"), CraftBlockScreenHandler::new);
	public static ScreenHandlerType<AlloyManufactoryScreenHandler> ALLOY_MANUFACTORY_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(More_Ores.MOD_ID, "alloy_manufactory"), AlloyManufactoryScreenHandler::new);
}
