package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Objects;

import static net.chocomint.more_ores.block.custom.ElectricTubeBlock.*;

public class ElectricTubeBlockEntity extends BlockEntity {

	public ElectricTubeBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ELECTRIC_TUBE_BLOCK_ENTITY, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, ElectricTubeBlockEntity entity) {
		// set connection
		TagsContain connect = d -> Registry.BLOCK.getOrCreateEntry(Registry.BLOCK
				.getKey(world.getBlockState(pos.add(d.getVector())).getBlock()).get()).isIn(ModTags.Blocks.CONNECTABLE);

		world.setBlockState(pos, state.with(CONNECT_TOP  , connect.matchTags(Direction.UP))
									  .with(CONNECT_DOWN , connect.matchTags(Direction.DOWN))
									  .with(CONNECT_WEST , connect.matchTags(Direction.WEST))
									  .with(CONNECT_EAST , connect.matchTags(Direction.EAST))
									  .with(CONNECT_NORTH, connect.matchTags(Direction.NORTH))
									  .with(CONNECT_SOUTH, connect.matchTags(Direction.SOUTH)));
	}

	interface TagsContain {
		boolean matchTags(Direction d);
	}
}
