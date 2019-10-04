package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;

public class SpookyFluidTags {
	public static final Tag<Fluid> WITCH_WATER = register("witch_water");

	private SpookyFluidTags() {
		// NO-OP
	}
	
	public static void init() {
	
	}

	private static Tag<Fluid> register(String name) {
		return new FluidTags.CachingTag(SpookyTime.id(name));
	}
}
