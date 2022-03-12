package net.chocomint.more_ores.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

import static net.chocomint.more_ores.block.custom.ElectricTubeBlock.*;
import static net.chocomint.more_ores.block.custom.ElectricTubeBlock.direction.*;

public class ElectricTubeBlockEntity extends BlockEntity {

	public ElectricTubeBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ELECTRIC_TUBE_BLOCK_ENTITY, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, ElectricTubeBlockEntity entity) {
		// set connection
		TagsContain connect = (direction d) -> Objects.requireNonNull(BlockTags.getTagGroup().getTag(new Identifier("c", "connectable")))
				.contains(world.getBlockState(pos.add(d.getRelativePos())).getBlock());

		world.setBlockState(pos, state.with(CONNECT_TOP  , connect.matchTags(top))
									  .with(CONNECT_DOWN , connect.matchTags(down))
									  .with(CONNECT_WEST , connect.matchTags(west))
									  .with(CONNECT_EAST , connect.matchTags(east))
									  .with(CONNECT_NORTH, connect.matchTags(north))
									  .with(CONNECT_SOUTH, connect.matchTags(south)));
	}

	interface TagsContain {
		boolean matchTags(direction d);
	}
}
