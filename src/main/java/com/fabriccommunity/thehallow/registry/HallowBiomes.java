package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.biome.GhastlyDesert;
import com.fabriccommunity.thehallow.world.biome.HauntedMoorBiome;
import com.fabriccommunity.thehallow.world.biome.HauntedUplandsBiome;
import com.fabriccommunity.thehallow.world.biome.LowlandBarrowsBiome;
import com.fabriccommunity.thehallow.world.biome.PumpkinPatchBiome;
import com.fabriccommunity.thehallow.world.biome.HallowBaseBiome;
import com.fabriccommunity.thehallow.world.biome.HallowForestBiome;
import com.fabriccommunity.thehallow.world.biome.HallowLowlandsBiome;
import com.fabriccommunity.thehallow.world.biome.HallowRiverBiome;
import com.fabriccommunity.thehallow.world.biome.HallowSeaBiome;
import com.fabriccommunity.thehallow.world.biome.HallowShoreBiome;
import com.fabriccommunity.thehallow.world.biome.HallowSwampBiome;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class HallowBiomes {
	// Hallowed Forest
	public static final HallowBaseBiome HALLOWED_FOREST = register("hallowed_forest", new HallowForestBiome(0.15f, 0.18f));
	public static final HallowBaseBiome HALLOWED_FOREST_HILLS = register("hallowed_forest_hills", new HallowForestBiome(0.25f, 0.2f));
	
	// Hallowed Lowlands
	public static final HallowBaseBiome HALLOWED_LOWLANDS = register("hallowed_lowlands", new HallowLowlandsBiome());
	public static final HallowBaseBiome HALLOWED_LOWLANDS_PUMPKINS = register("pumpkin_patch", new PumpkinPatchBiome());
	public static final HallowBaseBiome HALLOWED_LOWLANDS_BARROWS = register("hallowed_lowlands_barrows", new LowlandBarrowsBiome());
	
	//Ghastly Desert
	public static final HallowBaseBiome GHASTLY_DESERT = register("ghastly_desert", new GhastlyDesert());
	
	// Hallowed River
	public static final HallowBaseBiome HALLOWED_RIVER = register("hallowed_river", new HallowRiverBiome());
	
	// Hallowed Sea
	public static final HallowBaseBiome HALLOWED_SEA = register("hallowed_sea", new HallowSeaBiome());
	public static final HallowBaseBiome HALLOWED_SHORE = register("hallowed_shore", new HallowShoreBiome());
	
	// Hallowed Swamp
	public static final HallowBaseBiome HALLOWED_SWAMP = register("hallowed_swamp", new HallowSwampBiome());
	
	// Haunted Uplands
	public static final HallowBaseBiome HAUNTED_UPLANDS = register("haunted_uplands", new HauntedUplandsBiome());
	
	// Haunted Moor
	public static final HallowBaseBiome HAUNTED_MOOR = register("haunted_moor", new HauntedMoorBiome());
	
	private HallowBiomes() {
		// NO-OP
	}
	
	public static void init() {
		// Make default river the hallowed river in each biome
		HallowBaseBiome.BIOMES.forEach(biome -> OverworldBiomes.setRiverBiome(biome, HALLOWED_RIVER));
	}
	
	private static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, TheHallow.id(name), biome);
	}
}
