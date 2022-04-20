package net.chocomint.more_ores.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

import static net.chocomint.more_ores.block.ModBlocks.registerBlock;

public class ModWoodBlocks {

	public static final List<Pair<Block, Block>> STRIPPABLE_BLOCKS = new ArrayList<>();
	public static final List<Block> PLANKS_BLOCKS = new ArrayList<>();

	public static void registerWoodBlocks() {
		registerWoodBlock("hinoki", 6.0f);
	}

	private static void registerWoodBlock(String name, float strength) {
		Block W_LOG = registerBlock(name + "_log",
				new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(strength).requiresTool()));
		Block W_WOOD = registerBlock(name + "_wood",
				new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(strength).requiresTool()));
		Block STRIPPED_W_LOG = registerBlock("stripped_" + name + "_log",
				new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(strength).requiresTool()));
		Block STRIPPED_W_WOOD = registerBlock("stripped_" + name + "_wood",
				new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(strength).requiresTool()));
		Block W_PLANKS = registerBlock(name + "_planks",
				new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(strength).requiresTool()));

		STRIPPABLE_BLOCKS.add(new Pair<>(W_LOG, STRIPPED_W_LOG));
		STRIPPABLE_BLOCKS.add(new Pair<>(W_WOOD, STRIPPED_W_WOOD));
		PLANKS_BLOCKS.add(W_PLANKS);
	}
}
