package com.fabriccommunity.spookytime.world;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import java.util.ArrayList;
import java.util.List;

public class SpookyBiomeGroup {
	
	private static List<SpookyBiomeGroup> spookyBiomeGroups = new ArrayList<>();
	private static Int2ObjectMap<SpookyBiomeGroup> reverseMap = new Int2ObjectArrayMap<>();
	private final int id;
	private double weightTotal = 0;
	private List<WeightedBiomeEntry> entries = new ArrayList<>();
	
	public SpookyBiomeGroup(int id) {
		this.id = id;
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
	
	static class WeightedBiomeEntry {
		final Biome biome;
		final double weight;
		
		WeightedBiomeEntry(Biome biome, double weight) {
			this.biome = biome;
			this.weight = weight;
		}
	}
}
