package net.chocomint.more_ores.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import net.chocomint.more_ores.util.Utilities;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class LZHCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(CommandManager.literal("lzh")
				.then(CommandManager.argument("number", LongArgumentType.longArg())
				.executes(context -> {
					long n = context.getArgument("number", Long.class);
					context.getSource().getPlayer().sendMessage(new LiteralText("" + n), false);
					context.getSource().getPlayer().sendMessage(new LiteralText(Utilities.toLZHNumber(n)), false);
					return 1;
				})));
	}
}
