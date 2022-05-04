package net.chocomint.more_ores.fluid;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public abstract class RadioactiveWaterFluid extends FlowableFluid {

	@Override
	public Fluid getFlowing() {
		return ModFluids.RADIOACTIVE_WATER_FLOWING;
	}

	@Override
	public Fluid getStill() {
		return ModFluids.RADIOACTIVE_WATER_STILL;
	}

	@Override
	public Item getBucketItem() {
		return ModItems.RADIOACTIVE_WATER_BUCKET;
	}

	@Override
	protected void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
		if (!state.isStill() && !(Boolean)state.get(FALLING)) {
			if (random.nextInt(64) == 0) {
				world.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
			}
		} else if (random.nextInt(10) == 0) {
			world.addParticle(ParticleTypes.UNDERWATER, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Nullable
	@Override
	protected ParticleEffect getParticle() {
		return ParticleTypes.DRIPPING_WATER;
	}

	@Override
	protected boolean isInfinite() {
		return false;
	}

	@Override
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}

	@Override
	protected int getFlowSpeed(WorldView world) {
		return 4;
	}

	@Override
	public BlockState toBlockState(FluidState state) {
		return ModBlocks.RADIOACTIVE_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
	}

	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == ModFluids.RADIOACTIVE_WATER_STILL || fluid == ModFluids.RADIOACTIVE_WATER_FLOWING;
	}

	@Override
	public int getLevelDecreasePerBlock(WorldView world) {
		return 1;
	}

	@Override
	public int getTickRate(WorldView world) {
		return 5;
	}

	@Override
	public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
	}

	@Override
	protected float getBlastResistance() {
		return 100;
	}

	@Override
	public Optional<SoundEvent> getBucketFillSound() {
		return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
	}

	public static class Flowing extends RadioactiveWaterFluid {
		public Flowing() {}

		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}

		public int getLevel(FluidState state) {
			return state.get(LEVEL);
		}

		public boolean isStill(FluidState state) {
			return false;
		}
	}

	public static class Still extends RadioactiveWaterFluid {
		public Still() {}

		public int getLevel(FluidState state) {
			return 8;
		}

		public boolean isStill(FluidState state) {
			return true;
		}
	}
}
