package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.biome.GhastlyDesert;
import com.fabriccommunity.spookytime.world.biome.HauntedMoorBiome;
import com.fabriccommunity.spookytime.world.biome.HauntedUplandsBiome;
import com.fabriccommunity.spookytime.world.biome.LowlandBarrowsBiome;
import com.fabriccommunity.spookytime.world.biome.PumpkinPatchBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyBaseBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyLowlandsBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyRiverBiome;
import com.fabriccommunity.spookytime.world.biome.SpookySeaBiome;
import com.fabriccommunity.spookytime.world.biome.SpookyShoreBiome;
import com.fabriccommunity.spookytime.world.biome.SpookySwampBiome;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class SpookyBiomes {
	// Spooky Forest
	public static final SpookyBaseBiome SPOOKY_FOREST = register("spooky_forest", new SpookyForestBiome(0.15f, 0.18f));
	public static final SpookyBaseBiome SPOOKY_FOREST_HILLS = register("spooky_forest_hills", new SpookyForestBiome(0.25f, 0.2f));

	// Spooky Lowlands
	public static final SpookyBaseBiome SPOOKY_LOWLANDS = register("spooky_lowlands", new SpookyLowlandsBiome());
	public static final SpookyBaseBiome SPOOKY_LOWLANDS_PUMPKINS = register("pumpkin_patch", new PumpkinPatchBiome());
	public static final SpookyBaseBiome SPOOKY_LOWLANDS_BARROWS = register("spooky_lowlands_barrows", new LowlandBarrowsBiome());

	//Ghastly Desert
	public static final SpookyBaseBiome GHASTLY_DESERT = register("ghastly_desert", new GhastlyDesert());

	// Spooky River
	public static final SpookyBaseBiome SPOOKY_RIVER = register("spooky_river", new SpookyRiverBiome());

	// Spooky Sea
	public static final SpookyBaseBiome SPOOKY_SEA = register("spooky_sea", new SpookySeaBiome());
	public static final SpookyBaseBiome SPOOKY_SHORE = register("spooky_shore", new SpookyShoreBiome());

	// Spooky Swamp
	public static final SpookyBaseBiome SPOOKY_SWAMP = register("spooky_swamp", new SpookySwampBiome());

	// Haunted Uplands
	public static final SpookyBaseBiome HAUNTED_UPLANDS = register("haunted_uplands", new HauntedUplandsBiome());

	// Haunted Moor
	public static final SpookyBaseBiome HAUNTED_MOOR = register("haunted_moor", new HauntedMoorBiome());

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
