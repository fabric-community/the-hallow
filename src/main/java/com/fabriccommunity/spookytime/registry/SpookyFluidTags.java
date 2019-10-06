package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;

import com.fabriccommunity.spookytime.SpookyTime;

public class SpookyFluidTags {
	public static final Tag<Fluid> WITCH_WATER = register("witch_water");
	public static final Tag<Fluid> BLOOD = register("blood");
	
	private SpookyFluidTags() {
		// NO-OP
	}
	
	public static void init() {
	
	}
	
	private static Tag<Fluid> register(String name) {
		return TagRegistry.fluid(SpookyTime.id(name));
	}
}
