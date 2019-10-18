package com.fabriccommunity.thehallow.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.ArrayList;
import java.util.List;

public class HallowBiomeGroup {
	private static List<HallowBiomeGroup> hallowBiomeGroups = new ArrayList<HallowBiomeGroup>();
	private static Int2ObjectMap<HallowBiomeGroup> reverseMap = new Int2ObjectArrayMap<HallowBiomeGroup>();
	private final int id;
	private double weightTotal = 0;
	private List<WeightedBiomeEntry> entries = new ArrayList<WeightedBiomeEntry>();
	
	public HallowBiomeGroup(int id) {
		this.id = id;
	}
	
	public static int pickRandomBiomeGroup(LayerRandomnessSource rand) {
		return hallowBiomeGroups.get(rand.nextInt(hallowBiomeGroups.size())).id;
	}
	
	public static HallowBiomeGroup getById(int id) {
		return reverseMap.get(id);
	}
	
	public static HallowBiomeGroup addBiomeGroup(HallowBiomeGroup group) {
		hallowBiomeGroups.add(group);
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
	
	public HallowBiomeGroup addBiome(Biome biome, double weight) {
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
