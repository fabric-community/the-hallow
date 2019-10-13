package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.util.registry.Registry;

public class SpookyTags {
	public static final Tag<Item> COSTUMES = register("costumes");
	public static final Tag<Item> PUMPKINS = register("pumpkin");
	//public static final Tag<Item> CARVED_PUMPKIN = register("carved_pumpkin");
	//public static final Tag<Item> JACK_O_LANTERN = register("jack_o_lantern");


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
