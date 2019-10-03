package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.biome.LowlandBarrowsBiome;
import com.fabriccommunity.spookytime.world.biome.PumpkinPatchBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyBaseBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyLowlandsBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyRiverBiome;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class SpookyBiomes {
	// Spooky Forest
	public static final SpookyBaseBiome SPOOKY_FOREST = register("spooky_forest", new SpookyForestBiome());
	
	// Spooky Lowlands
	public static final SpookyBaseBiome SPOOKY_LOWLANDS = register("spooky_lowlands", new SpookyLowlandsBiome());
	public static final SpookyBaseBiome SPOOKY_LOWLANDS_PUMPKINS = register("pumpkin_patch", new PumpkinPatchBiome());
	public static final SpookyBaseBiome SPOOKY_LOWLANDS_BARROWS = register("spooky_lowlands_barrows", new LowlandBarrowsBiome());
	
	// Spooky River
	public static final SpookyBaseBiome SPOOKY_RIVER = register("spooky_river", new SpookyRiverBiome());
	
	private SpookyBiomes() {
		// NO-OP
	}
	
	public static void init() {
		// Make default river the spooky river in each biome
		SpookyBaseBiome.BIOMES.forEach(biome -> OverworldBiomes.setRiverBiome(biome, SPOOKY_RIVER));
	}
	
	private static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, SpookyTime.id(name), biome);
	}
}
