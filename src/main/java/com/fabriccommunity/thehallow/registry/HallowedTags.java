package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedTags {
	public static final Tag<Item> COSTUMES = item("costumes");
	public static final Tag<Block> GATE_CIRCLE = block("gate_circle");
	public static final Tag<Item> PUMPKIN_FOODS = item("pumpkin_foods");
  public static final Tag<Block> PUMPKINS = block("pumpkins");
	public static final Tag<Block> CARVED_PUMPKINS = block("carved_pumpkins");
	public static final Tag<Block> JACK_O_LANTERNS = block("jack_o_lanterns");
  public static final Tag<Item> COMMON_PUMPKINS = item("common_pumpkins");
	
	private HallowedTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static Tag<Block> block(String name) {
		return TagRegistry.block(TheHallow.id(name));
	}
	
	public static Tag<Item> item(String name) {
		return TagRegistry.item(TheHallow.id(name));
	}
	
	public static Tag<Fluid> fluid(String name) {
		return TagRegistry.fluid(TheHallow.id(name));
	}
	
	public static Tag<EntityType<?>> entityType(String name) {
		return TagRegistry.entityType(TheHallow.id(name));
	}
}
