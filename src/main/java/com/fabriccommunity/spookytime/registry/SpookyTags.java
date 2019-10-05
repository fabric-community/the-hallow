package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;

public class SpookyTags {
	public static Tag<Item> COSTUMES = register("costumes");
	
	private SpookyTags() {
		// NO-OP
	}
	
	public static void init() {
	
	}
	
	public static Tag<Item> register(String name) {
		return TagRegistry.item(SpookyTime.id(name));
	}
}
