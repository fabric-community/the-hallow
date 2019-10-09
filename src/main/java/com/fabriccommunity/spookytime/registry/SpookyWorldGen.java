package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.world.SpookyBiomeGroup;
import com.fabriccommunity.spookytime.world.layer.AddSpookyEdgeLayer;
import com.fabriccommunity.spookytime.world.layer.AddSpookyShoreLayer;
import com.fabriccommunity.spookytime.world.layer.AddSubBiomesLayer;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.world.biome.Biome;

public class SpookyWorldGen {
	public static final SpookyBiomeGroup SPOOKY = new SpookyBiomeGroup(0)
		.addBiome(SpookyBiomes.SPOOKY_FOREST, 1)
		.addBiome(SpookyBiomes.SPOOKY_LOWLANDS, 1)
		.addBiome(SpookyBiomes.SPOOKY_SWAMP, 1)
		.addBiome(SpookyBiomes.GHASTLY_DESERT, 1);

	public static final SpookyBiomeGroup SEA_ISLANDS = new SpookyBiomeGroup(1)
		.addBiome(SpookyBiomes.SPOOKY_SEA, 1);

	public static final SpookyBiomeGroup UPLANDS = new SpookyBiomeGroup(2)
		.addBiome(SpookyBiomes.HAUNTED_UPLANDS, 1)
		.addBiome(SpookyBiomes.HAUNTED_MOOR, 1);

	private SpookyWorldGen() {
		// NO-OP
	}

	public static void init() {
		// Add dimension biome placement stuff here

		addLargeSubBiome(SpookyBiomes.SPOOKY_LOWLANDS, SpookyBiomes.SPOOKY_LOWLANDS_PUMPKINS, 0.33f);
		addHillsSubBiome(SpookyBiomes.SPOOKY_LOWLANDS, SpookyBiomes.SPOOKY_LOWLANDS_BARROWS);

		addHillsSubBiome(SpookyBiomes.SPOOKY_FOREST, SpookyBiomes.SPOOKY_FOREST_HILLS);

		// Islands
		addSmallSubBiome(SpookyBiomes.SPOOKY_SEA, SpookyBiomes.SPOOKY_LOWLANDS, 0.22f);
		addSmallSubBiome(SpookyBiomes.SPOOKY_SEA, SpookyBiomes.SPOOKY_FOREST, 0.22f);
		addSmallSubBiome(SpookyBiomes.SPOOKY_SEA, SpookyBiomes.GHASTLY_DESERT, 0.22f);
		addSmallSubBiome(SpookyBiomes.SPOOKY_SEA, SpookyBiomes.SPOOKY_LOWLANDS_PUMPKINS, 0.22f);

		// Uplands Edge
		setEdgeBiome(SpookyBiomes.HAUNTED_MOOR, SpookyBiomes.HAUNTED_UPLANDS);

		// Rivers
		setRiverBiome(SpookyBiomes.SPOOKY_SEA, null);
		setRiverBiome(SpookyBiomes.SPOOKY_SHORE, SpookyBiomes.SPOOKY_SEA);

		setRiverBiome(SpookyBiomes.HAUNTED_MOOR, null);
		setRiverBiome(SpookyBiomes.HAUNTED_UPLANDS, null);

		// Make SPOOKY more common by adding twice
		addBiomeGroups(SPOOKY, SPOOKY, SEA_ISLANDS, UPLANDS);
	}

	private static void addBiomeGroups(SpookyBiomeGroup... biomeGroups) {
		for (SpookyBiomeGroup group : biomeGroups) {
			SpookyBiomeGroup.addBiomeGroup(group);
		}
	}

	private static void addLargeSubBiome(Biome parent, Biome subBiome, double chance) {
		AddSubBiomesLayer.LARGE.addSubBiome(parent, subBiome, chance);
	}

	private static void addSmallSubBiome(Biome parent, Biome subBiome, double chance) {
		AddSubBiomesLayer.SMALL.addSubBiome(parent, subBiome, chance);
	}

	private static void addHillsSubBiome(Biome parent, Biome hillsBiome) {
		AddSubBiomesLayer.HILLS.addSubBiome(parent, hillsBiome, 0.3f);
	}

	private static void setRiverBiome(Biome parent, Biome river) {
		OverworldBiomes.setRiverBiome(parent, river);
	}

	private static void setEdgeBiome(Biome parent, Biome edge) {
		AddSpookyEdgeLayer.INSTANCE.setEdgeBiome(parent, edge);
	}

	private static void setShoreBiome(Biome parent, Biome shore) {
		AddSpookyShoreLayer.DEFAULT.setEdgeBiome(parent, shore);
	}
}
