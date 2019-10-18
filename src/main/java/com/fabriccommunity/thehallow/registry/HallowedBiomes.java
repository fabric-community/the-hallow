package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.biome.GhastlyDesert;
import com.fabriccommunity.thehallow.world.biome.HauntedMoorBiome;
import com.fabriccommunity.thehallow.world.biome.HauntedUplandsBiome;
import com.fabriccommunity.thehallow.world.biome.LowlandBarrowsBiome;
import com.fabriccommunity.thehallow.world.biome.PumpkinPatchBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedBaseBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedForestBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedLowlandsBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedRiverBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedSeaBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedShoreBiome;
import com.fabriccommunity.thehallow.world.biome.HallowedSwampBiome;

public class HallowedBiomes {
	// Hallowed Forest
	public static final HallowedBaseBiome HALLOWED_FOREST = register("hallowed_forest", new HallowedForestBiome(0.15f, 0.18f));
	public static final HallowedBaseBiome HALLOWED_FOREST_HILLS = register("hallowed_forest_hills", new HallowedForestBiome(0.25f, 0.2f));
	
	// Hallowed Lowlands
	public static final HallowedBaseBiome HALLOWED_LOWLANDS = register("hallowed_lowlands", new HallowedLowlandsBiome());
	public static final HallowedBaseBiome HALLOWED_LOWLANDS_PUMPKINS = register("pumpkin_patch", new PumpkinPatchBiome());
	public static final HallowedBaseBiome HALLOWED_LOWLANDS_BARROWS = register("hallowed_lowlands_barrows", new LowlandBarrowsBiome());
	
	//Ghastly Desert
	public static final HallowedBaseBiome GHASTLY_DESERT = register("ghastly_desert", new GhastlyDesert());
	
	// Hallowed River
	public static final HallowedBaseBiome HALLOWED_RIVER = register("hallowed_river", new HallowedRiverBiome());
	
	// Hallowed Sea
	public static final HallowedBaseBiome HALLOWED_SEA = register("hallowed_sea", new HallowedSeaBiome());
	public static final HallowedBaseBiome HALLOWED_SHORE = register("hallowed_shore", new HallowedShoreBiome());
	
	// Hallowed Swamp
	public static final HallowedBaseBiome HALLOWED_SWAMP = register("hallowed_swamp", new HallowedSwampBiome());
	
	// Haunted Uplands
	public static final HallowedBaseBiome HAUNTED_UPLANDS = register("haunted_uplands", new HauntedUplandsBiome());
	
	// Haunted Moor
	public static final HallowedBaseBiome HAUNTED_MOOR = register("haunted_moor", new HauntedMoorBiome());
	
	private HallowedBiomes() {
		// NO-OP
	}
	
	public static void init() {
		// Make default river the hallowed river in each biome
		HallowedBaseBiome.BIOMES.forEach(biome -> OverworldBiomes.setRiverBiome(biome, HALLOWED_RIVER));
	}
	
	private static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, TheHallow.id(name), biome);
	}
}
