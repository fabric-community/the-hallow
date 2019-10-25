package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedTags {
	public static final Tag<Item> COSTUMES = register("costumes");
	public static final Tag<Block> GATE_CIRCLE = TagRegistry.block(TheHallow.id("gate_circle"));
	
	private HallowedTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static Tag<Item> register(String name) {
		return TagRegistry.item(TheHallow.id(name));
	}
}
