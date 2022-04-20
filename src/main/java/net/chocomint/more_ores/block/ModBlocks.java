package net.chocomint.more_ores.block;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.custom.*;
import net.chocomint.more_ores.item.ModItemGroup;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ModBlocks {

	// Ores
	public static final Block LEAD_ORE = registerBlock("lead_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(2.5f).requiresTool()));
	public static final Block TITANIUM_ORE = registerBlock("titanium_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f).requiresTool()));
	public static final Block SILVER_ORE = registerBlock("silver_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f).requiresTool()));

	public static final Block CHARGED_QUARTZ_ORE = registerBlock("charged_quartz_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(12.0f).requiresTool()));

	public static final Block END_VIBRANIUM_ORE = registerBlock("end_vibranium_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(8.0f).requiresTool()));
	public static final Block END_AQUAMARINE_NYLIUM = registerBlock("end_aquamarine_nylium",
			new Block(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()));

	// Stones
	public static final Block MARBLE = registerBlock("marble",
			new Block(FabricBlockSettings.of(Material.STONE).strength(1.8f).requiresTool()));
	public static final Block SERPENTINE = registerBlock("serpentine",
			new Block(FabricBlockSettings.of(Material.STONE).strength(2.0f).requiresTool()));

	// Machines
	public static final Block ALLOY_MANUFACTORY = registerBlock("alloy_manufactory",
			new AlloyManufactoryBlock(FabricBlockSettings.of(Material.METAL)));
	public static final Block FILLER = registerBlock("filler",
			new FillerBlock(FabricBlockSettings.of(Material.METAL)));
	public static final Block ATM = registerBlock("atm",
			new ATMBlock(FabricBlockSettings.of(Material.METAL)));


	// General Blocks
	public static final Block TOUGHENED_GLASS = registerBlock("toughened_glass",
			new GlassBlock(FabricBlockSettings.of(Material.GLASS).strength(15.0f).requiresTool().nonOpaque()));
	public static final Block ELECTRIC_TUBE = registerBlock("electric_tube",
			new ElectricTubeBlock(FabricBlockSettings.of(Material.METAL)));

	public static final Block STONE_BRICKS_PEDESTAL = registerBlock("stone_bricks_pedestal",
			new PedestalBlock(FabricBlockSettings.of(Material.STONE).strength(8f)));
	public static final Block DIAMOND_PEDESTAL = registerBlock("diamond_pedestal",
			new PedestalBlock(FabricBlockSettings.of(Material.STONE).strength(10f)));


	// Functions
	protected static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registry.BLOCK, new Identifier(More_Ores.MOD_ID, name), block);
	}

	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registry.ITEM, new Identifier(More_Ores.MOD_ID, name),
				new BlockItem(block, new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	}

	public static void registerModBlocks() {
		ModWoodBlocks.registerWoodBlocks();
		System.out.println("Registering Mod Blocks for" + More_Ores.MOD_ID);
	}

	public static void renderGlassBlock(Block... block) {
		for(int i = 0 ; i < block.length ; i++) { BlockRenderLayerMap.INSTANCE.putBlock(List.of(block).get(i), RenderLayer.getCutout()); }
	}
}
