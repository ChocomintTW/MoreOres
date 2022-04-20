package net.chocomint.more_ores.item.custom;

import net.chocomint.more_ores.util.IEntityDataSaver;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class GravityGunItem extends Item {
	public GravityGunItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (!isUsing(Objects.requireNonNull(context.getPlayer()).getMainHandStack())) {
			World world = context.getWorld();
			PlayerEntity player = context.getPlayer();
			BlockPos pos = context.getBlockPos();

//			// spawn falling block
//			FallingBlockEntity e = FallingBlockEntity.spawnFromBlock(world, pos, world.getBlockState(pos));
//
//			IEntityDataSaver p = (IEntityDataSaver) player;
		}
		else
			context.getPlayer().getMainHandStack().getOrCreateNbt().putBoolean("isUsing", false);
		return ActionResult.SUCCESS;
	}

	public static boolean isUsing(ItemStack stack) {
		return stack.getOrCreateNbt().getBoolean("isUsing");
	}
}
