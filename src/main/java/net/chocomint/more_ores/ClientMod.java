package net.chocomint.more_ores;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.fluid.ModFluids;
import net.chocomint.more_ores.screen.ATMScreen;
import net.chocomint.more_ores.screen.AlloyManufactoryScreen;
import net.chocomint.more_ores.screen.FillerScreen;
import net.chocomint.more_ores.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALLOY_MANUFACTORY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FILLER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ATM, RenderLayer.getCutout());

		ScreenRegistry.register(ModScreenHandlers.ALLOY_MANUFACTORY_SCREEN_HANDLER, AlloyManufactoryScreen::new);
		ScreenRegistry.register(ModScreenHandlers.FILLER_SCREEN_HANDLER, FillerScreen::new);
		ScreenRegistry.register(ModScreenHandlers.ATM_SCREEN_HANDLER, ATMScreen::new);

		FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.RADIOACTIVE_WATER_STILL,
				new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
						SimpleFluidRenderHandler.WATER_FLOWING,
						SimpleFluidRenderHandler.WATER_OVERLAY, 0x34E547));
		FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.RADIOACTIVE_WATER_FLOWING,
				new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
						SimpleFluidRenderHandler.WATER_FLOWING,
						SimpleFluidRenderHandler.WATER_OVERLAY, 0x34E547));

		System.out.println("Client Mod!");
	}
}
