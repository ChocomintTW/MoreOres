package net.chocomint.more_ores.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemoteControlItem extends Item {
	public RemoteControlItem(Settings settings) {
		super(settings.maxCount(1));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		int[] v = stack.getOrCreateNbt().getIntArray("bound_block");
		if (v.length == 3) {
			BlockPos pos = new BlockPos(v[0], v[1], v[2]);

			if (Screen.hasShiftDown())
				tooltip.add(new TranslatableText("tooltip.remote_control.shift", toString(pos)));
			else {
				tooltip.add(new TranslatableText("tooltip.remote_control.line1"));
				tooltip.add(new TranslatableText("tooltip.remote_control.line2"));
			}
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient() && hand == Hand.MAIN_HAND) {

			int[] v = user.getMainHandStack().getOrCreateNbt().getIntArray("bound_block");
			if (v.length == 3) {
				BlockPos pos = new BlockPos(v[0], v[1], v[2]);
				BlockState state = world.getBlockState(pos);
				if (state.getBlock() == Blocks.REDSTONE_LAMP) {
					boolean lit = state.get(RedstoneLampBlock.LIT);
					world.setBlockState(pos, state.with(RedstoneLampBlock.LIT, !lit));
					user.sendMessage(new TranslatableText(!lit ? "message.remote_control.lit" : "message.remote_control.no_lit",
							new TranslatableText("block.minecraft.redstone_lamp"), toString(pos)), true);
				}
			}
		}
		return super.use(world, user, hand);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		BlockPos pos = context.getBlockPos();

		if (world.isClient() && world.getBlockState(pos).getBlock() == Blocks.REDSTONE_LAMP) {
			assert player != null;
			if (player.isSneaking()) {
				NbtCompound nbt = new NbtCompound();
				nbt.putIntArray("bound_block", new int[]{pos.getX(), pos.getY(), pos.getZ()});
				player.getMainHandStack().setNbt(nbt);
				player.sendMessage(new TranslatableText("message.remote_control.bound",
						new TranslatableText("block.minecraft.redstone_lamp"), toString(pos)), false);
			}
		}
		return super.useOnBlock(context);
	}

	public static BlockPos getBlockPos(ItemStack stack) {
		int[] v = stack.getOrCreateNbt().getIntArray("bound_block");
		return new BlockPos(v[0], v[1], v[2]);
	}

	public static String toString(BlockPos pos) {
		return "[" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]";
	}
}
