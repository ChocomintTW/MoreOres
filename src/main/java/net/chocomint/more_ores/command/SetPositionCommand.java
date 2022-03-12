package net.chocomint.more_ores.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.chocomint.more_ores.util.IEntityDataSaver;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;

public class SetPositionCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(CommandManager.literal("position")
				.then(CommandManager.literal("set")
				.then(CommandManager.argument("pos_name", StringArgumentType.string())

				.executes(SetPositionCommand::run))));
	}

	public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();
		BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
		String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

		player.getPersistentData().putIntArray("pos", new int[] {playerPos.getX(), playerPos.getY(), playerPos.getZ() });

		String pos_name = context.getArgument("pos_name", String.class);
		System.out.println(pos_name);
		player.getPersistentData().putString("pos_name", pos_name);

		context.getSource().sendFeedback(new LiteralText("Set " + pos_name  + " at " + pos), true);
		return 1;
	}
}
