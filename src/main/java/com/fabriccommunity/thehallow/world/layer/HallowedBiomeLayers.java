package com.fabriccommunity.thehallow.world.layer;

import java.util.function.LongFunction;

import net.minecraft.world.biome.layer.AddRiversLayer;
import net.minecraft.world.biome.layer.NoiseToRiverLayer;
import net.minecraft.world.biome.layer.ScaleLayer;
import net.minecraft.world.biome.layer.SimpleLandNoiseLayer;
import net.minecraft.world.biome.layer.SmoothenShorelineLayer;
import net.minecraft.world.biome.layer.util.CachingLayerContext;
import net.minecraft.world.biome.layer.util.LayerFactory;
import net.minecraft.world.biome.layer.util.LayerSampleContext;
import net.minecraft.world.biome.layer.util.LayerSampler;
import net.minecraft.world.biome.source.BiomeLayerSampler;

public class HallowedBiomeLayers {
	private static final int biomeSize = 4; // 3 should be the *minimum* value for this
	
	private static <R extends LayerSampler, T extends LayerSampleContext<R>> LayerFactory<R> create(LongFunction<T> contextProvider) {
		// Biome Groups
		LayerFactory<R> biomeInit = SetBiomeGroupsLayer.INSTANCE.create(contextProvider.apply(1L));
		biomeInit = ScaleLayer.NORMAL.create(contextProvider.apply(100L), biomeInit);
		
		// Biomes
		biomeInit = AddBiomesLayer.INSTANCE.create(contextProvider.apply(20L), biomeInit);
		LayerFactory<R> biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1000L), biomeInit);
		
		// Sub Biomes and stuff
		biomes = AddSubBiomesLayer.LARGE.create(contextProvider.apply(50L), biomes);
		biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), biomes);
		
		biomes = AddHallowedEdgeLayer.INSTANCE.create(contextProvider.apply(54L), biomes);
		biomes = AddSubBiomesLayer.HILLS.create(contextProvider.apply(51L), biomes);
		biomes = AddSubBiomesLayer.SMALL.create(contextProvider.apply(53L), biomes);
		
		for (int i = 0; i < biomeSize; ++i) {
			biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1000L + (long) i), biomes);
			
			if (biomeSize == 1 || i == 1) {
				biomes = AddHallowedShoreLayer.DEFAULT.create(contextProvider.apply(52L), biomes);
			}
		}
		
		biomes = SmoothenShorelineLayer.INSTANCE.create(contextProvider.apply(101L), biomes);
		
		// River
		LayerFactory<R> rivers = SimpleLandNoiseLayer.INSTANCE.create(contextProvider.apply(200L), biomeInit);
		
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1000L), rivers);
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), rivers);
		for (int i = 0; i < biomeSize - 1; ++i) {
			rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1000L + (long) i), rivers);
		}
		
		// Noise to river
		rivers = NoiseToRiverLayer.INSTANCE.create(contextProvider.apply(201L), rivers);
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), rivers);
		
		// Mix rivers with biomes
		biomes = AddRiversLayer.INSTANCE.create(contextProvider.apply(102L), biomes, rivers);
		
		return biomes;
	}
	
	public static BiomeLayerSampler build(long seed) {
		return new BiomeLayerSampler(create((salt) -> new CachingLayerContext(25, seed, salt)));
	}
}

