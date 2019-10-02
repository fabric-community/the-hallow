package com.fabriccommunity.spookytime.world.layer;

import java.util.function.LongFunction;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.biome.layer.AddRiversLayer;
import net.minecraft.world.biome.layer.BiomeLayerSampler;
import net.minecraft.world.biome.layer.CachingLayerContext;
import net.minecraft.world.biome.layer.CachingLayerSampler;
import net.minecraft.world.biome.layer.CellScaleLayer;
import net.minecraft.world.biome.layer.LayerFactory;
import net.minecraft.world.biome.layer.LayerSampleContext;
import net.minecraft.world.biome.layer.LayerSampler;
import net.minecraft.world.biome.layer.NoiseToRiverLayer;
import net.minecraft.world.biome.layer.ScaleLayer;
import net.minecraft.world.biome.layer.SimpleLandNoiseLayer;
import net.minecraft.world.biome.layer.SmoothenShorelineLayer;

public class SpookyBiomeLayers {
	
	private static final int biomeSize = 4; // 3 should be the *minimum* value for this
	
	private static <R extends LayerSampler, T extends LayerSampleContext<R>> ImmutableList<LayerFactory<R>> create(LongFunction<T> contextProvider) {
		// Biome Groups
		LayerFactory<R> biomeInit = SetBiomeGroupsLayer.INSTANCE.create(contextProvider.apply(1L));
		biomeInit = ScaleLayer.NORMAL.create(contextProvider.apply(100L), biomeInit);
		
		// Biomes
		biomeInit = AddBiomesLayer.INSTANCE.create(contextProvider.apply(20L), biomeInit);
		LayerFactory<R> biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1000L), biomeInit);
		
		// Sub Biomes and stuff
		biomes = AddSubBiomesLayer.LARGE.create(contextProvider.apply(50L), biomes);
		biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), biomes);
		
		biomes = AddSubBiomesLayer.HILLS.create(contextProvider.apply(51L), biomes);
		
		for (int i = 0; i < biomeSize; ++i) {
			biomes = ScaleLayer.NORMAL.create(contextProvider.apply(1000L + (long) i), biomes);
		}
		
		biomes = SmoothenShorelineLayer.INSTANCE.create(contextProvider.apply(101L), biomes);
		
		// River
		LayerFactory<R> rivers = SimpleLandNoiseLayer.INSTANCE.create(contextProvider.apply(200L), biomeInit);
		
		// Scaling based off of the biome layer scaling
		// So that rivers generate around the biome edges
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1000L), rivers);
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), rivers);
		for (int i = 0; i < biomeSize - 2; ++i) {
			rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1000L + (long) i), rivers);
		}
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1000L), rivers);
		
		// Noise to river
		rivers = NoiseToRiverLayer.INSTANCE.create(contextProvider.apply(201L), rivers);
		rivers = ScaleLayer.NORMAL.create(contextProvider.apply(1001L), rivers);
		
		// Mix rivers with biomes
		biomes = AddRiversLayer.INSTANCE.create(contextProvider.apply(102L), biomes, rivers);
		
		// Voronoi cell 4x zoom for normal biome sampling
		
		// NOTE FOR UPDATING TO 1.15:
		// in 1.15 snapshots this logic is moved to another class
		LayerFactory<R> voronoi = CellScaleLayer.INSTANCE.create(contextProvider.apply(4L), biomes);
		
		return ImmutableList.of(biomes, voronoi);
	}
	
	public static BiomeLayerSampler[] build(long seed) {
		ImmutableList<LayerFactory<CachingLayerSampler>> list = create((salt) -> new CachingLayerContext(25, seed, salt));
		
		BiomeLayerSampler noiseLayer = new BiomeLayerSampler(list.get(0));
		BiomeLayerSampler biomeLayer = new BiomeLayerSampler(list.get(1));
		
		return new BiomeLayerSampler[] {noiseLayer, biomeLayer};
	}
}

	