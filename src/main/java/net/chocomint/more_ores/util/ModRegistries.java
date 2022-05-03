package net.chocomint.more_ores.util;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModWoodBlocks;
import net.chocomint.more_ores.command.ReturnPositionCommand;
import net.chocomint.more_ores.command.SetPositionCommand;
import net.chocomint.more_ores.command.LZHCommand;
import net.chocomint.more_ores.events.PlayerEvents;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.util.trade.Trade;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import oshi.util.tuples.Pair;

import static net.chocomint.more_ores.block.ModWoodBlocks.*;

public class ModRegistries {

	public static void registerAllRegistries() {
		registerModFuel();
		registerCommands();
		registerEvents();
		registerCustomTrades();
		registerStrippableBlocks();
		registerFlammableBlocks();
		registerAnotherRecipe();
	}

	public static void registerAnotherRecipe() {
	}

	public static void registerModFuel() {
		System.out.println("Registering Fuels for " + More_Ores.MOD_ID);
		FuelRegistry fuel = FuelRegistry.INSTANCE;

		fuel.add(ModItems.ADVANCED_COAL, 12000);
	}

	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(SetPositionCommand::register);
		CommandRegistrationCallback.EVENT.register(ReturnPositionCommand::register);
		CommandRegistrationCallback.EVENT.register(LZHCommand::register);
	}

	public static void registerEvents() {
		ServerPlayerEvents.COPY_FROM.register(new PlayerEvents());
	}

	public static void registerCustomTrades() {
		for(Trade info : Trade.ModTrades)
			TradeOfferHelper.registerVillagerOffers(info.profession(), info.level(),
					factories -> factories.add((entity, random) -> info.tradeOffer()));
	}

	public static void registerStrippableBlocks() {
		for (Pair<Block, Block> p : STRIPPABLE_BLOCKS)
			StrippableBlockRegistry.register(p.getA(), p.getB());
	}

	public static void registerFlammableBlocks() {
		FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

		for (Pair<Block, Block> b : ModWoodBlocks.STRIPPABLE_BLOCKS) {
			instance.add(b.getA(), 5, 5);
			instance.add(b.getB(), 5, 5);
		}
		for (Block b : ModWoodBlocks.PLANKS_BLOCKS)
			instance.add(b, 5, 20);
	}
}
