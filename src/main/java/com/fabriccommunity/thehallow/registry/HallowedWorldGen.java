package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;

import net.minecraft.world.biome.Biome;

import com.fabriccommunity.thehallow.world.HallowedBiomeGroup;
import com.fabriccommunity.thehallow.world.layer.AddHallowedEdgeLayer;
import com.fabriccommunity.thehallow.world.layer.AddHallowedShoreLayer;
import com.fabriccommunity.thehallow.world.layer.AddSubBiomesLayer;

public class HallowedWorldGen {
	public static final HallowedBiomeGroup SPOOKY = new HallowedBiomeGroup(0)
		.addBiome(HallowedBiomes.SPOOKY_FOREST, 1)
		.addBiome(HallowedBiomes.SPOOKY_LOWLANDS, 1)
		.addBiome(HallowedBiomes.SPOOKY_SWAMP, 1)
		.addBiome(HallowedBiomes.GHASTLY_DESERT, 1);
	
	public static final HallowedBiomeGroup SEA_ISLANDS = new HallowedBiomeGroup(1)
		.addBiome(HallowedBiomes.SPOOKY_SEA, 1);
	
	public static final HallowedBiomeGroup UPLANDS = new HallowedBiomeGroup(2)
		.addBiome(HallowedBiomes.HAUNTED_UPLANDS, 1)
		.addBiome(HallowedBiomes.HAUNTED_MOOR, 1);
	
	private HallowedWorldGen() {
		// NO-OP
	}
	
	public static void init() {
		// Add dimension biome placement stuff here
		
		addLargeSubBiome(HallowedBiomes.SPOOKY_LOWLANDS, HallowedBiomes.SPOOKY_LOWLANDS_PUMPKINS, 0.33f);
		addHillsSubBiome(HallowedBiomes.SPOOKY_LOWLANDS, HallowedBiomes.SPOOKY_LOWLANDS_BARROWS);
		
		addHillsSubBiome(HallowedBiomes.SPOOKY_FOREST, HallowedBiomes.SPOOKY_FOREST_HILLS);
		
		// Islands
		addSmallSubBiome(HallowedBiomes.SPOOKY_SEA, HallowedBiomes.SPOOKY_LOWLANDS, 0.22f);
		addSmallSubBiome(HallowedBiomes.SPOOKY_SEA, HallowedBiomes.SPOOKY_FOREST, 0.22f);
		addSmallSubBiome(HallowedBiomes.SPOOKY_SEA, HallowedBiomes.GHASTLY_DESERT, 0.22f);
		addSmallSubBiome(HallowedBiomes.SPOOKY_SEA, HallowedBiomes.SPOOKY_LOWLANDS_PUMPKINS, 0.22f);
		
		// Uplands Edge
		setEdgeBiome(HallowedBiomes.HAUNTED_MOOR, HallowedBiomes.HAUNTED_UPLANDS);
		
		// Rivers
		setRiverBiome(HallowedBiomes.SPOOKY_SEA, null);
		setRiverBiome(HallowedBiomes.SPOOKY_SHORE, HallowedBiomes.SPOOKY_SEA);
		
		setRiverBiome(HallowedBiomes.HAUNTED_MOOR, null);
		setRiverBiome(HallowedBiomes.HAUNTED_UPLANDS, null);
		
		// Make SPOOKY more common by adding twice
		addBiomeGroups(SPOOKY, SPOOKY, SEA_ISLANDS, UPLANDS);
	}
	
	private static void addBiomeGroups(HallowedBiomeGroup... biomeGroups) {
		for (HallowedBiomeGroup group : biomeGroups) {
			HallowedBiomeGroup.addBiomeGroup(group);
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
		AddHallowedEdgeLayer.INSTANCE.setEdgeBiome(parent, edge);
	}
	
	private static void setShoreBiome(Biome parent, Biome shore) {
		AddHallowedShoreLayer.DEFAULT.setEdgeBiome(parent, shore);
	}
}
