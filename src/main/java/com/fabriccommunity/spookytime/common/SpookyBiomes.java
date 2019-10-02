package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class SpookyBiomes {
	public static final SpookyForestBiome SPOOKY_FOREST = register("spooky_forest", new SpookyForestBiome());
	
	private SpookyBiomes() {
		// NO-OP
	}
	
	public static void init() {
	
	}
	
	private static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, SpookyTime.id(name), biome);
	}
}
