package net.chocomint.more_ores.block.custom;

import net.chocomint.more_ores.block.entity.ElectricTubeBlockEntity;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ElectricTubeBlock extends BlockWithEntity {
	private static final VoxelShape CENTER = Block.createCuboidShape(5D,  5D,  5D,  11D, 11D, 11D);
	private static final VoxelShape TOP    = Block.createCuboidShape(5D,  11D, 5D,  11D, 16D, 11D);
	private static final VoxelShape DOWN   = Block.createCuboidShape(5D,  0D,  5D,  11D, 5D,  11D);
	private static final VoxelShape EAST   = Block.createCuboidShape(11D, 5D,  5D,  16D, 11D, 11D);
	private static final VoxelShape WEST   = Block.createCuboidShape(0D,  5D,  5D,  5D,  11D, 11D);
	private static final VoxelShape NORTH  = Block.createCuboidShape(5D,  5D,  0D,  11D, 11D, 5D);
	private static final VoxelShape SOUTH  = Block.createCuboidShape(5D,  5D,  11D, 11D, 11D, 16D);
	private static final VoxelShape EMPTY  = Block.createCuboidShape(0,0,0,0,0,0);

	public ElectricTubeBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Optional<ElectricTubeBlockEntity> blockEntity = world.getBlockEntity(pos, ModBlockEntities.ELECTRIC_TUBE_BLOCK_ENTITY);
		if(blockEntity.isPresent()) {
			boolean[] connect = blockEntity.get().getConnect();
			return VoxelShapes.union(CENTER, connect[0] ? WEST  : EMPTY
										   , connect[1] ? EAST  : EMPTY
										   , connect[2] ? DOWN  : EMPTY
										   , connect[3] ? TOP   : EMPTY
										   , connect[4] ? NORTH : EMPTY
										   , connect[5] ? SOUTH : EMPTY);
		}
		return CENTER;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ElectricTubeBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ModBlockEntities.ELECTRIC_TUBE_BLOCK_ENTITY, ElectricTubeBlockEntity::tick);
	}
}
