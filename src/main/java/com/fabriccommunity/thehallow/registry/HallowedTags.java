package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedTags {
	public static class Items {
		private Items() {
			// NO-OP
		}
		public static final Tag<Item> COSTUMES = registerItem("costumes");
		public static final Tag<Item> COMMON_PUMPKINS = registerItem("common_pumpkins");
		public static final Tag<Item> PUMPKINS = registerItem("pumpkins");
		public static final Tag<Item> CARVED_PUMPKINS = registerItem("carved_pumpkins");
		public static final Tag<Item> JACK_O_LANTERNS = registerItem("jack_o_lanterns");
	}

	public static class Blocks {
		private Blocks() {
			// NO-OP
		}
    public static final Tag<Block> GATE_CIRCLE = registerBlock("gate_circle");
		public static final Tag<Block> PUMPKINS = registerBlock("pumpkins");
		public static final Tag<Block> CARVED_PUMPKINS = registerBlock("carved_pumpkins");
		public static final Tag<Block> JACK_O_LANTERNS = registerBlock("jack_o_lanterns");
	}
	
	private HallowedTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static Tag<Item> registerItem(String name) {
		return TagRegistry.item(TheHallow.id(name));
	}
	public static Tag<Block> registerBlock(String name) {
		return TagRegistry.block(TheHallow.id(name));
	}
}
