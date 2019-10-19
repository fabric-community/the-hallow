package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.spookytime.SpookyTime;

public class SpookyTags {
	public static final Tag<Item> COSTUMES = register("costumes");
	
	private SpookyTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static Tag<Item> register(String name) {
		return TagRegistry.item(SpookyTime.id(name));
	}
}
