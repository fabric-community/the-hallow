package com.fabriccommunity.spookytime.common.world;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

public class SpookyBiomeGroup {
	
	private static List<SpookyBiomeGroup> spookyBiomeGroups = new ArrayList<>();
	private static Int2ObjectMap<SpookyBiomeGroup> reverseMap = new Int2ObjectArrayMap<>();
	
	public SpookyBiomeGroup(int id) {
		this.id = id;
	}
	
	private final int id;
	
	private double weightTotal = 0;
	private List<WeightedBiomeEntry> entries = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public Biome pickBiome(LayerRandomnessSource random) {
		double randVal = (double) random.nextInt(Integer.MAX_VALUE) * weightTotal / (double) Integer.MAX_VALUE;
		int i = -1;
		while (randVal >= 0) {
			++i;
			randVal -= entries.get(i).weight;
		}
		return entries.get(i).biome;
	}
	
	public SpookyBiomeGroup addBiome(Biome biome, double weight) {
		this.weightTotal += weight;
		this.entries.add(new WeightedBiomeEntry(biome, weight));
		
		return this;
	}
	
	public static int pickRandomBiomeGroup(LayerRandomnessSource rand) {
		return spookyBiomeGroups.get(rand.nextInt(spookyBiomeGroups.size())).id;
	}
	
	public static SpookyBiomeGroup getById(int id) {
		return reverseMap.get(id);
	}
	
	public static SpookyBiomeGroup addBiomeGroup(SpookyBiomeGroup group) {
		spookyBiomeGroups.add(group);
		reverseMap.put(group.id, group);
		return group;
	}
	
	static class WeightedBiomeEntry {
		WeightedBiomeEntry(Biome biome, double weight) {
			this.biome = biome;
			this.weight = weight;
		}
		
		final Biome biome;
		final double weight;
	}
}
