package com.fabriccommunity.thehallow.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;
import com.fabriccommunity.thehallow.registry.HallowedBiomes;
import com.fabriccommunity.thehallow.world.layer.HallowedBiomeLayers;
import com.google.common.collect.ImmutableSet;

public class HallowedBiomeSource extends BiomeSource {
	
	public final BiomeLayerSampler biomeLayer, noiseLayer;
	
	public HallowedBiomeSource(long seed) {
		super(ImmutableSet.of(
				HallowedBiomes.HALLOWED_FOREST,
				HallowedBiomes.HALLOWED_FOREST_HILLS,
				HallowedBiomes.HALLOWED_LOWLANDS,
				HallowedBiomes.HALLOWED_LOWLANDS_BARROWS,
				HallowedBiomes.HALLOWED_LOWLANDS_PUMPKINS,
				HallowedBiomes.GHASTLY_DESERT,
				HallowedBiomes.HALLOWED_RIVER,
				HallowedBiomes.HALLOWED_SEA,
				HallowedBiomes.HALLOWED_SHORE,
				HallowedBiomes.HALLOWED_SWAMP,
				HallowedBiomes.HAUNTED_UPLANDS,
				HallowedBiomes.HAUNTED_MOOR));
		BiomeLayerSampler[] samplers = HallowedBiomeLayers.build(seed);
		
		noiseLayer = samplers[0];
		biomeLayer = samplers[1];
	}
	
	@Override
	public Biome getStoredBiome(int x, int y, int z) {
		return biomeLayer.sample(x, z);
	}
	
	public Biome getBiomeForNoiseGen(int x, int z) {
		return biomeLayer.sample(x, z);
	}
}
