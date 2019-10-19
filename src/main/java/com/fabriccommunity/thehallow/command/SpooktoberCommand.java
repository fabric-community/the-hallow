package com.fabriccommunity.thehallow.command;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import com.mojang.brigadier.context.CommandContext;

import com.fabriccommunity.thehallow.util.TimeUtil;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class SpooktoberCommand {
	public static int run(CommandContext<ServerCommandSource> ctx) {
		
		long daysLeft = TimeUtil.daysTillSpooktober();
		
		if (daysLeft != 0) {
			ctx.getSource().sendFeedback(new TranslatableText("thehallow.cmd.tillspooktober", daysLeft).formatted(Formatting.GRAY), false);
			return 0;
		}
		
		ctx.getSource().sendFeedback(new TranslatableText("thehallow.cmd.spooktober").formatted(Formatting.GOLD, Formatting.BOLD), false);
		return SINGLE_SUCCESS;
	}
}
