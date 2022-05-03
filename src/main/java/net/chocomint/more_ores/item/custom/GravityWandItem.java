package net.chocomint.more_ores.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GravityWandItem extends Item {
	public static FallingBlockEntity FBE;
	public static PlayerEntity PLAYER;
	public static double DISTANCE;
	public static boolean ON_USE = false;

	public GravityWandItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient() && hand == Hand.MAIN_HAND)
		{
			if (ON_USE) {
				FBE.setNoGravity(false);
				FBE.setOnGround(true);
				ON_USE = false;
			} else {
				ON_USE = true;
				HitResult hit = user.raycast(5, 0f, false);
				BlockPos pos = new BlockPos(hit.getPos());
				DISTANCE = hit.squaredDistanceTo(user);
				Block block = world.getBlockState(pos).getBlock();
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
				FBE = FallingBlockEntity.spawnFromBlock(world, pos, block.getDefaultState());
				FBE.setOnGround(false);
				FBE.setNoGravity(true);
				PLAYER = user;
			}
			return TypedActionResult.success(user.getStackInHand(hand));
		}
		return TypedActionResult.fail(user.getStackInHand(hand));
	}
}
