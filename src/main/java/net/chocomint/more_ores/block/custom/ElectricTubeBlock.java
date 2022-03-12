package net.chocomint.more_ores.block.custom;

import net.chocomint.more_ores.block.entity.ElectricTubeBlockEntity;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElectricTubeBlock extends BlockWithEntity {
	// Properties
	public static final IntProperty POWER = IntProperty.of("power", 0, 10000);
	public static final BooleanProperty CONNECT_TOP   = BooleanProperty.of("top");
	public static final BooleanProperty CONNECT_DOWN  = BooleanProperty.of("down");
	public static final BooleanProperty CONNECT_EAST  = BooleanProperty.of("east");
	public static final BooleanProperty CONNECT_WEST  = BooleanProperty.of("west");
	public static final BooleanProperty CONNECT_NORTH = BooleanProperty.of("north");
	public static final BooleanProperty CONNECT_SOUTH = BooleanProperty.of("south");

	// Voxel Shapes
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
		this.setDefaultState(this.getDefaultState()
				.with(CONNECT_TOP,   false)
				.with(CONNECT_DOWN,  false)
				.with(CONNECT_EAST,  false)
				.with(CONNECT_WEST,  false)
				.with(CONNECT_SOUTH, false)
				.with(CONNECT_NORTH, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(CONNECT_TOP, CONNECT_DOWN, CONNECT_EAST, CONNECT_WEST, CONNECT_SOUTH, CONNECT_NORTH);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(CENTER, state.get(CONNECT_TOP)   ? TOP   : EMPTY
									   , state.get(CONNECT_DOWN)  ? DOWN  : EMPTY
									   , state.get(CONNECT_EAST)  ? EAST  : EMPTY
									   , state.get(CONNECT_WEST)  ? WEST  : EMPTY
									   , state.get(CONNECT_NORTH) ? NORTH : EMPTY
									   , state.get(CONNECT_SOUTH) ? SOUTH : EMPTY);
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

	public enum direction {
		top  (0, 1, 0 ),
		down (0, -1,0 ),
		east (1, 0, 0 ),
		west (-1,0, 0 ),
		north(0, 0, -1),
		south(0, 0, 1 );

		private final Vec3i relative_pos;

		direction(int x, int y, int z) {
			this.relative_pos = new Vec3i(x, y, z);
		}

		public Vec3i getRelativePos() {
			return relative_pos;
		}
	}
}
