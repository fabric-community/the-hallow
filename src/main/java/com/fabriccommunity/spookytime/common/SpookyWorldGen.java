package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.world.SpookyBiomeGroup;
import com.fabriccommunity.spookytime.world.layer.AddSubBiomesLayer;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.world.biome.Biome;

public class SpookyWorldGen {
	
	public static void init() {
		// Add dimension biome placement stuff here
		SpookyBiomeGroup DEFAULT = new SpookyBiomeGroup(0)
				.addBiome(SpookyBiomes.SPOOKY_FOREST, 1)
				.addBiome(SpookyBiomes.SPOOKY_LOWLANDS, 1);
		
		addLargeSubBiome(SpookyBiomes.SPOOKY_LOWLANDS, SpookyBiomes.SPOOKY_LOWLANDS_PUMPKINS, 0.33f);
		addHillsSubBiome(SpookyBiomes.SPOOKY_LOWLANDS, SpookyBiomes.SPOOKY_LOWLANDS_BARROWS);
		
		addBiomeGroups(DEFAULT);
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
	
}
