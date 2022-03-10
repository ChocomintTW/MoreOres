package net.chocomint.more_ores.block;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.custom.AlloyManufactoryBlock;
import net.chocomint.more_ores.block.custom.ElectricTubeBlock;
import net.chocomint.more_ores.block.custom.FillerBlock;
import net.chocomint.more_ores.block.custom.StatusBlock;
import net.chocomint.more_ores.item.ModItemGroup;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ModBlocks {

	// Ores
	public static final Block LEAD_ORE = registerBlock("lead_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(2.5f)
					.breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));
	public static final Block TITANIUM_ORE = registerBlock("titanium_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f)
					.breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));
	public static final Block SILVER_ORE = registerBlock("silver_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f)
					.breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));
	public static final Block VOID_GEM_ORE = registerBlock("void_gem_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(7.0f)
					.breakByTool(FabricToolTags.PICKAXES, 4).requiresTool()));

	public static final Block CHARGED_QUARTZ_ORE = registerBlock("charged_quartz_ore",
			new Block(FabricBlockSettings.of(Material.STONE).strength(12.0f)
					.breakByTool(FabricToolTags.PICKAXES, 4).requiresTool()));

	// Stones
	public static final Block MARBLE = registerBlock("marble",
			new Block(FabricBlockSettings.of(Material.STONE).strength(1.8f)
					.breakByTool(FabricToolTags.PICKAXES, 0).requiresTool()));
	public static final Block SERPENTINE = registerBlock("serpentine",
			new Block(FabricBlockSettings.of(Material.STONE).strength(2.0f)
					.breakByTool(FabricToolTags.PICKAXES, 0).requiresTool()));

	// Machines
	public static final Block ALLOY_MANUFACTORY = registerBlock("alloy_manufactory",
			new AlloyManufactoryBlock(FabricBlockSettings.of(Material.METAL)));
	public static final Block FILLER = registerBlock("filler",
			new FillerBlock(FabricBlockSettings.of(Material.METAL)));


	// General Blocks

	public static final Block STATUS_BLOCK = registerBlock("status_block",
			new StatusBlock(FabricBlockSettings.of(Material.STONE)));
	public static final Block TOUGHENED_GLASS = registerBlock("toughened_glass",
			new GlassBlock(FabricBlockSettings.of(Material.GLASS).strength(15.0f)
					.breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque()));
	public static final Block ELECTRIC_TUBE = registerBlock("electric_tube",
			new ElectricTubeBlock(FabricBlockSettings.of(Material.METAL)));


	// Functions
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registry.BLOCK, new Identifier(More_Ores.MOD_ID, name), block);
	}

	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registry.ITEM, new Identifier(More_Ores.MOD_ID, name),
				new BlockItem(block, new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	}

	public static void registerModBlocks() {
		System.out.println("Registering Mod Blocks for" + More_Ores.MOD_ID);
	}

	public static void renderGlassBlock(Block... block) {
		for(int i = 0 ; i < block.length ; i++) { BlockRenderLayerMap.INSTANCE.putBlock(List.of(block).get(i), RenderLayer.getCutout()); }
	}
}
