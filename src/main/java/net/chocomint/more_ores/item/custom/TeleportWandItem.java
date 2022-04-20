package net.chocomint.more_ores.item.custom;

import net.chocomint.more_ores.util.AimedSurfaceCheck;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportWandItem extends Item {
	public TeleportWandItem(Settings settings) {
		super(settings.maxCount(1).maxDamage(500));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new TranslatableText("tooltip.teleport_wand.max_distance", 30));
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
					Vec3f tpPos = new Vec3f(Vec3d.ofCenter(aimedBlock));
					Direction d = AimedSurfaceCheck.check(player, aimedBlock);
					tpPos.add(d.getUnitVector());
					player.teleport(tpPos.getX(), tpPos.getY(), tpPos.getZ());
					return super.use(world, player, hand);
				}
			}
			player.sendMessage(new LiteralText("Max distance is 30 blocks!"), false);
		}
		return super.use(world, player, hand);
	}

	@Override
	public int getEnchantability() {
		return 1;
	}

	interface positionHelper {
		Vec3d getPos(double mul);
	}
}
