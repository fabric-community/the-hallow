package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.common.world.SpookyBiomeGroup;
import com.fabriccommunity.spookytime.common.world.layer.AddSubBiomesLayer;

import net.minecraft.world.biome.Biome;

public class SpookyWorldGen {
	
	public static void init() {
		// Add dimension biome placement stuff here
		SpookyBiomeGroup DEFAULT = new SpookyBiomeGroup(0)
				.addBiome(SpookyBiomes.SPOOKY_FOREST, 1);
		
		addBiomeGroups(DEFAULT);
	}
	
	private static void addBiomeGroups(SpookyBiomeGroup...biomeGroups) {
		for (SpookyBiomeGroup group : biomeGroups) {
			SpookyBiomeGroup.addBiomeGroup(group);
		}
	}
	
	private static void addLargeSubBiome(Biome parent, Biome subBiome, double chance) {
		AddSubBiomesLayer.LARGE.addSubBiome(parent, subBiome, chance);
	}
	
	private static void addSmallSubBiome(Biome parent, Biome subBiome, double chance) {
		AddSubBiomesLayer.SMALL.addSubBiome(parent, subBiome, chance);
	}
	
	private static void addHillsBiome(Biome parent, Biome hillsBiome) {
		AddSubBiomesLayer.HILLS.addSubBiome(parent, hillsBiome, 0.3f);
	}

}
