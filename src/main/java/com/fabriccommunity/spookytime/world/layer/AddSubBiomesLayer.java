package com.fabriccommunity.spookytime.world.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import net.minecraft.world.biome.layer.SouthEastSamplingLayer;

public enum AddSubBiomesLayer implements SouthEastSamplingLayer {
	LARGE,
	HILLS,
	SMALL;
	
	private final Map<Biome, List<ChanceBiomeEntry>> biomes = new HashMap<>();
	
	@Override
	public int sample(LayerRandomnessSource rand, int value) {
		Biome biome = Registry.BIOME.get(value);
		
		if (biomes.containsKey(biome)) {
			Biome result = biome;
			Iterator<ChanceBiomeEntry> iterator = biomes.get(biome).iterator();
			
			while (iterator.hasNext()) {
				ChanceBiomeEntry entry = iterator.next();
				if (nextDouble(rand) < entry.chance) {
					result = entry.biome;
					break;
				}
			}
			
			return Registry.BIOME.getRawId(result);
		}
		
		return value;
	}
	
	public void addSubBiome(Biome parent, Biome subBiome, double chance) {
		this.biomes.computeIfAbsent(parent, b -> new ArrayList<>()).add(new ChanceBiomeEntry(subBiome, chance));
	}
	
	private static double nextDouble(LayerRandomnessSource rand) {
		return (double) rand.nextInt(Integer.MAX_VALUE) / (double) Integer.MAX_VALUE;
	}
	
	static class ChanceBiomeEntry {
		ChanceBiomeEntry(Biome biome, double chance) {
			this.biome = biome;
			this.chance = chance;
		}
		
		final Biome biome;
		final double chance;
	}
}
