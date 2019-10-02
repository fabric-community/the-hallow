package com.fabriccommunity.spookytime.common;

import static net.minecraft.server.command.CommandManager.literal;

import com.fabriccommunity.spookytime.common.cmd.ContibutorsCommand;
import com.fabriccommunity.spookytime.common.cmd.SpooktoberCommand;
import com.fabriccommunity.spookytime.common.cmd.SpookyCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.ServerCommandSource;

public class SpookyCommands {

	public static void init() {
		CommandRegistry.INSTANCE.register(false, SpookyCommands::register);
	}

	private static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

		RootCommandNode<ServerCommandSource> root = dispatcher.getRoot();

		LiteralCommandNode<ServerCommandSource> baseCmd = literal("spook").executes(SpookyCommand::run).build();

		LiteralCommandNode<ServerCommandSource> spooktober = literal("spooktober").executes(SpooktoberCommand::run).build();

		LiteralCommandNode<ServerCommandSource> contributors = literal("contributors").executes(ContibutorsCommand::run).build();

		root.addChild(baseCmd);
		root.addChild(spooktober);

		baseCmd.addChild(spooktober);
		baseCmd.addChild(contributors);

		dispatcher.register(literal("spooky-time").redirect(baseCmd));
	}

	private SpookyCommands() {
		// NO-OP
	}
}
