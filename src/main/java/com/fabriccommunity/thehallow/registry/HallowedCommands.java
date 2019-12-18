package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.registry.CommandRegistry;

import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import com.fabriccommunity.thehallow.command.ContibutorsCommand;
import com.fabriccommunity.thehallow.command.HallowedCommand;
import com.fabriccommunity.thehallow.command.SpooktoberCommand;

import static net.minecraft.server.command.CommandManager.literal;

public class HallowedCommands {
	private HallowedCommands() {
		// NO-OP
	}
	
	public static void init() {
		CommandRegistry.INSTANCE.register(false, HallowedCommands::register);
	}
	
	private static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		RootCommandNode<ServerCommandSource> root = dispatcher.getRoot();
		
		LiteralCommandNode<ServerCommandSource> baseCmd = literal("spook").executes(HallowedCommand::run).build();
		
		LiteralCommandNode<ServerCommandSource> spooktober = literal("spooktober").executes(SpooktoberCommand::run).build();
		
		LiteralCommandNode<ServerCommandSource> contributors = literal("contributors").executes(ContibutorsCommand::run).build();
		
		root.addChild(baseCmd);
		root.addChild(spooktober);
		
		baseCmd.addChild(spooktober);
		baseCmd.addChild(contributors);
		
		dispatcher.register(literal("the-hallow").redirect(baseCmd));
	}
}
