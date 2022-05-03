package net.chocomint.more_ores.item.custom;

import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.util.AimedSurfaceCheck;
import net.chocomint.more_ores.util.enchantment.ModEnchantments;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
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
		tooltip.add(new TranslatableText("tooltip.teleport_wand.max_distance", getMaxTeleportDistance(stack)));
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return ingredient.getItem() == ModItems.TELEPORT_CORE;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClient() && hand == Hand.MAIN_HAND) {

			float yaw = player.getYaw();
			float pitch = player.getPitch();
			// [ 0, 0] -> +z / south
			// [90, 0] -> -x / west
			positionHelper pos = mul -> Vec3d.fromPolar(pitch, yaw).multiply(mul).add(player.getEyePos());

			ItemStack stack = player.getMainHandStack();
			int max_tp = getMaxTeleportDistance(stack);
			stack.getOrCreateNbt().putInt("max_tp_distance", max_tp);

			for (int i = 1; i <= max_tp; i++) {
				BlockPos aimedBlock = new BlockPos(pos.getPos(i));

				if (world.getBlockState(aimedBlock).getBlock() != Blocks.AIR) {

					Vec3f tpPos = new Vec3f(Vec3d.ofCenter(aimedBlock));
					Direction d = AimedSurfaceCheck.check(player, aimedBlock);
					tpPos.add(d.getUnitVector());

					if (world.getBlockState(new BlockPos(new Vec3d(tpPos))).getBlock() == Blocks.AIR) {
						double distance = player.getPos().distanceTo(new Vec3d(tpPos));

						player.teleport(tpPos.getX(), tpPos.getY(), tpPos.getZ());
						player.getMainHandStack().damage(distanceToDamage(distance),
								player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
					}
					else if (world.getBlockState(new BlockPos(new Vec3d(tpPos).add(0, 1, 0))).getBlock() == Blocks.AIR) {
						tpPos.add(0, 1, 0);
						double distance = player.getPos().distanceTo(new Vec3d(tpPos));

						player.teleport(tpPos.getX(), tpPos.getY(), tpPos.getZ());
						player.getMainHandStack().damage(distanceToDamage(distance),
								player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
					}
					else
						player.sendMessage(new TranslatableText("message.teleport_wand.block").formatted(Formatting.RED), true);

					return super.use(world, player, hand);
				}
			}
			player.sendMessage(new TranslatableText("message.teleport_wand.max", max_tp).formatted(Formatting.RED), true);
		}
		return super.use(world, player, hand);
	}

	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
		NbtCompound nbt = new NbtCompound();
		nbt.putInt("max_tp_distance", 30);
		stack.setNbt(nbt);
	}

	private static int getMaxTeleportDistance(ItemStack stack) {
		return 30 + EnchantmentHelper.getLevel(ModEnchantments.DISTANCE_ADDITION, stack) * 5;
	}

	private static int distanceToDamage(double distance) {
		return (int) Math.ceil(distance / 10);
	}

	@Override
	public int getEnchantability() {
		return 15;
	}

	interface positionHelper {
		Vec3d getPos(double mul);
	}
}
