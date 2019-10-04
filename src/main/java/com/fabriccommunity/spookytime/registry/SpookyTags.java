package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;

import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;

public class SpookyTags {
	public static Tag<Item> COSTUMES = new ItemTags.CachingTag(SpookyTime.id("costumes"));
	
	private SpookyTags() {
		// NO-OP
	}
	
	public static void init() {
	
	}
}
