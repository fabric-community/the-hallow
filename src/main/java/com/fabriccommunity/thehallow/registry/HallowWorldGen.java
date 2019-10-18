package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;

import net.minecraft.world.biome.Biome;

import com.fabriccommunity.thehallow.world.HallowBiomeGroup;
import com.fabriccommunity.thehallow.world.layer.AddHallowEdgeLayer;
import com.fabriccommunity.thehallow.world.layer.AddHallowShoreLayer;
import com.fabriccommunity.thehallow.world.layer.AddSubBiomesLayer;

public class HallowWorldGen {
	public static final HallowBiomeGroup HALLOW = new HallowBiomeGroup(0)
		.addBiome(HallowBiomes.HALLOWED_FOREST, 1)
		.addBiome(HallowBiomes.HALLOWED_LOWLANDS, 1)
		.addBiome(HallowBiomes.HALLOWED_SWAMP, 1)
		.addBiome(HallowBiomes.GHASTLY_DESERT, 1);
	
	public static final HallowBiomeGroup SEA_ISLANDS = new HallowBiomeGroup(1)
		.addBiome(HallowBiomes.HALLOWED_SEA, 1);
	
	public static final HallowBiomeGroup UPLANDS = new HallowBiomeGroup(2)
		.addBiome(HallowBiomes.HAUNTED_UPLANDS, 1)
		.addBiome(HallowBiomes.HAUNTED_MOOR, 1);
	
	private HallowWorldGen() {
		// NO-OP
	}
	
	public static void init() {
		// Add dimension biome placement stuff here
		
		addLargeSubBiome(HallowBiomes.HALLOWED_LOWLANDS, HallowBiomes.HALLOWED_LOWLANDS_PUMPKINS, 0.33f);
		addHillsSubBiome(HallowBiomes.HALLOWED_LOWLANDS, HallowBiomes.HALLOWED_LOWLANDS_BARROWS);
		
		addHillsSubBiome(HallowBiomes.HALLOWED_FOREST, HallowBiomes.HALLOWED_FOREST_HILLS);
		
		// Islands
		addSmallSubBiome(HallowBiomes.HALLOWED_SEA, HallowBiomes.HALLOWED_LOWLANDS, 0.22f);
		addSmallSubBiome(HallowBiomes.HALLOWED_SEA, HallowBiomes.HALLOWED_FOREST, 0.22f);
		addSmallSubBiome(HallowBiomes.HALLOWED_SEA, HallowBiomes.GHASTLY_DESERT, 0.22f);
		addSmallSubBiome(HallowBiomes.HALLOWED_SEA, HallowBiomes.HALLOWED_LOWLANDS_PUMPKINS, 0.22f);
		
		// Uplands Edge
		setEdgeBiome(HallowBiomes.HAUNTED_MOOR, HallowBiomes.HAUNTED_UPLANDS);
		
		// Rivers
		setRiverBiome(HallowBiomes.HALLOWED_SEA, null);
		setRiverBiome(HallowBiomes.HALLOWED_SHORE, HallowBiomes.HALLOWED_SEA);
		
		setRiverBiome(HallowBiomes.HAUNTED_MOOR, null);
		setRiverBiome(HallowBiomes.HAUNTED_UPLANDS, null);
		
		// Make HALLOWED more common by adding twice
		addBiomeGroups(HALLOW, HALLOW, SEA_ISLANDS, UPLANDS);
	}
	
	private static void addBiomeGroups(HallowBiomeGroup... biomeGroups) {
		for (HallowBiomeGroup group : biomeGroups) {
			HallowBiomeGroup.addBiomeGroup(group);
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
		AddHallowEdgeLayer.INSTANCE.setEdgeBiome(parent, edge);
	}
	
	private static void setShoreBiome(Biome parent, Biome shore) {
		AddHallowShoreLayer.DEFAULT.setEdgeBiome(parent, shore);
	}
}
