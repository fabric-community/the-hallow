package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.command.ContibutorsCommand;
import com.fabriccommunity.spookytime.command.SpooktoberCommand;
import com.fabriccommunity.spookytime.command.SpookyCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class SpookyCommands {
	private SpookyCommands() {
		// NO-OP
	}
	
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
}
