package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;

public class SpookyFluidTags {
	public static final Tag<Fluid> WITCH_WATER = register("witch_water");
	public static final Tag<Fluid> BLOOD = register("blood");

	private SpookyFluidTags() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	private static Tag<Fluid> register(String name) {
		return TagRegistry.fluid(SpookyTime.id(name));
	}
}
