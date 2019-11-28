package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedTags {
	public static class Fluids {
		public static final Tag<Fluid> WITCH_WATER = register("witch_water");
		public static final Tag<Fluid> BLOOD = register("blood");
		
		public static Tag<Fluid> register(String name) {
			return TagRegistry.fluid(TheHallow.id(name));
		}
		
		protected static void init() {
			// NO-OP
		}
	}
	
	public static class Items {
		public static final Tag<Item> COSTUMES = register("costumes");
		public static final Tag<Item> FEATHERS = register("feathers");
		public static final Tag<Item> COBBLESTONE = register("cobblestone");
		public static final Tag<Item> PUMPKIN_FOODS = register("pumpkin_foods");
		public static final Tag<Item> COMMON_PUMPKINS = register("common_pumpkins");
		public static final Tag<Item> PUMPKINS = register("pumpkins");
		public static final Tag<Item> CARVED_PUMPKINS = register("carved_pumpkins");
		public static final Tag<Item> JACK_O_LANTERNS = register("jack_o_lanterns");
		
		public static Tag<Item> register(String name) {
			return TagRegistry.item(TheHallow.id(name));
		}
		
		protected static void init() {
			// NO-OP
		}
	}
	
	public static class Blocks {
		public static final Tag<Block> COBBLESTONE = register("cobblestone");
		public static final Tag<Block> GATE_CIRCLE = register("gate_circle");
		public static final Tag<Block> PUMPKINS = register("pumpkins");
		public static final Tag<Block> CARVED_PUMPKINS = register("carved_pumpkins");
		public static final Tag<Block> JACK_O_LANTERNS = register("jack_o_lanterns");
		
		public static Tag<Block> register(String name) {
			return TagRegistry.block(TheHallow.id(name));
		}
		
		protected static void init() {
			// NO-OP
		}
	}
	
	private HallowedTags() {
		// NO-OP
	}
	
	public static void init() {
		Fluids.init();
		Items.init();
		Blocks.init();
	}
}
