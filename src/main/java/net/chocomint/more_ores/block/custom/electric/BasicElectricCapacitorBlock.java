package net.chocomint.more_ores.block.custom.electric;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasicElectricCapacitorBlock extends ElectricCapacitor {
	public BasicElectricCapacitorBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		world.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing().getOpposite()));
	}
}
