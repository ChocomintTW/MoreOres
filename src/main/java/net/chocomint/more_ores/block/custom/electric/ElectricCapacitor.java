package net.chocomint.more_ores.block.custom.electric;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;

public class ElectricCapacitor extends Block {
	public static final DirectionProperty FACING = DirectionProperty.of("facing");
	public static final IntProperty POWER = IntProperty.of("power", 0, 10000);

	public ElectricCapacitor(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(POWER, 0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWER);
	}
}
