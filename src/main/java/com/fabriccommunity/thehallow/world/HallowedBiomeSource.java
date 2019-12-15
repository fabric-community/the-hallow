package com.fabriccommunity.thehallow.world;

import com.fabriccommunity.thehallow.registry.HallowedBiomes;
import com.fabriccommunity.thehallow.world.layer.HallowedBiomeLayers;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;

public class HallowedBiomeSource extends BiomeSource {
	
	public final BiomeLayerSampler biomeSampler;
	
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
		
		biomeSampler = HallowedBiomeLayers.build(seed);
	}
	
	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) { // these represent sub chunk values, not xyz
		return biomeSampler.sample(biomeX, biomeZ);
	}
}
