package com.fabriccommunity.spookytime.command;

import com.fabriccommunity.spookytime.util.TimeUtil;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class SpooktoberCommand {
	public static int run(CommandContext<ServerCommandSource> ctx) {

		long daysLeft = TimeUtil.daysTillSpooktober();

		if (daysLeft != 0) {
			ctx.getSource().sendFeedback(new TranslatableText("spookytime.cmd.tillspooktober", daysLeft).formatted(Formatting.GRAY), false);
			return 0;
		}

		ctx.getSource().sendFeedback(new TranslatableText("spookytime.cmd.spooktober").formatted(Formatting.GOLD, Formatting.BOLD), false);
		return 1;
	}
}
