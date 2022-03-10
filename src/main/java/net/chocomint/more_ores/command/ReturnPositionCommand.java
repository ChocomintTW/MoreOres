package net.chocomint.more_ores.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.chocomint.more_ores.util.IEntityDataSaver;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class ReturnPositionCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(CommandManager.literal("position")
				.then(CommandManager.literal("return")
				.then(CommandManager.argument("pos_name", StringArgumentType.greedyString()))

				.executes(ReturnPositionCommand::run)));
	}

	private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();

		if(player.getPersistentData().getIntArray("pos").length != 0) {
			int[] playerPos = player.getPersistentData().getIntArray("pos");
			context.getSource().getPlayer().requestTeleport(playerPos[0], playerPos[1], playerPos[2]);

			String pos_name = context.getArgument("pos_name", String.class);
			context.getSource().sendFeedback(new LiteralText("Player returned " + pos_name + "!"), true);
			return 1;
		}
		else {
			context.getSource().sendFeedback(new LiteralText("No valid position has been Set!"), true);
			return -1;
		}
	}
}
