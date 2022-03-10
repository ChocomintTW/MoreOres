package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ElectricTubeBlockEntity extends BlockEntity {
	private int electricity = 0;
	private boolean[] connect = {false, false, false, false, false, false};

	public enum direction {	neg_x, pos_x, neg_y, pos_y, neg_z, pos_z }

	public ElectricTubeBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ELECTRIC_TUBE_BLOCK_ENTITY, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, ElectricTubeBlockEntity entity) {
		// set connection
		entity.setConnect(direction.neg_x, world.getBlockState(pos.add(-1,0 ,0 )).getBlock() == ModBlocks.ELECTRIC_TUBE);
		entity.setConnect(direction.pos_x, world.getBlockState(pos.add(1 ,0 ,0 )).getBlock() == ModBlocks.ELECTRIC_TUBE);
		entity.setConnect(direction.neg_y, world.getBlockState(pos.add(0 ,-1,0 )).getBlock() == ModBlocks.ELECTRIC_TUBE);
		entity.setConnect(direction.pos_y, world.getBlockState(pos.add(0 ,1 ,0 )).getBlock() == ModBlocks.ELECTRIC_TUBE);
		entity.setConnect(direction.neg_z, world.getBlockState(pos.add(0 ,0 ,-1)).getBlock() == ModBlocks.ELECTRIC_TUBE);
		entity.setConnect(direction.pos_z, world.getBlockState(pos.add(0 ,0 ,1 )).getBlock() == ModBlocks.ELECTRIC_TUBE);
	}

	public void setElectricity(int value) {
		this.electricity = value;
	}

	public void addElectricity(int value) {
		this.electricity += value;
	}

	public int getElectricity() {
		return electricity;
	}

	public void setConnect(direction dir, boolean bool) {
		this.connect[dir.ordinal()] = bool;
	}

	public boolean[] getConnect() {
		return connect;
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
	}
}
