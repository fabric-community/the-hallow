package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedFluidTags {
	public static final Tag<Fluid> WITCH_WATER = register("witch_water");
	public static final Tag<Fluid> BLOOD = register("blood");
	
	private HallowedFluidTags() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	private static Tag<Fluid> register(String name) {
		return TagRegistry.fluid(TheHallow.id(name));
	}
}
