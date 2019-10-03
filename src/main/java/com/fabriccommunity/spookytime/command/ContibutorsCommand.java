package com.fabriccommunity.spookytime.command;

import com.fabriccommunity.spookytime.util.Contributors;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class ContibutorsCommand {
	public static int run(CommandContext<ServerCommandSource> ctx) {
		Contributors.sendContributorsMessage(ctx.getSource());
		return SINGLE_SUCCESS;
	}
}
