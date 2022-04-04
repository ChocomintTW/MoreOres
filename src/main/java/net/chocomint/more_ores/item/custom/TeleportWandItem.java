package net.chocomint.more_ores.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportWandItem extends Item {
	public TeleportWandItem(Settings settings) {
		super(settings.maxCount(1));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText("Max teleport distance: 30"));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClient() && hand == Hand.MAIN_HAND) {
			float yaw = player.getYaw();
			float pitch = player.getPitch();
			// [ 0, 0] -> +z / south
			// [90, 0] -> -x / west
			positionHelper pos = mul -> Vec3d.fromPolar(pitch, yaw).multiply(mul).add(player.getEyePos());

			for (int i = 1; i <= 30; i++) {
				BlockPos aimedBlock = new BlockPos(pos.getPos(i));

				if (world.getBlockState(aimedBlock).getBlock() != Blocks.AIR) {
					BlockPos former = new BlockPos(pos.getPos(i - 1));
					player.teleport(former.getX(), former.getY(), former.getZ());
					return super.use(world, player, hand);
				}
			}
			player.sendMessage(new LiteralText("Max distance is 30 blocks!"), false);
		}
		return super.use(world, player, hand);
	}

	interface positionHelper {
		Vec3d getPos(double mul);
	}
}
