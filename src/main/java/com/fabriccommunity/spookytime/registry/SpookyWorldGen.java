package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.SpookyBiomeGroup;
import com.fabriccommunity.spookytime.world.layer.AddSubBiomesLayer;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.WildCropFeature;

public class SpookyWorldGen {
    public static final SpookyBiomeGroup DEFAULT = new SpookyBiomeGroup(0)
            .addBiome(SpookyBiomes.SPOOKY_FOREST, 1);

    void SpookyWorldGen() {
        // NO-OP
    }
	
	public static void init() {
		addBiomeGroups(DEFAULT);
	}
	
	private static void addBiomeGroups(SpookyBiomeGroup... biomeGroups) {
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
	
	private static void setRiverBiome(Biome parent, Biome river) {
		OverworldBiomes.setRiverBiome(parent, river);
	}
}
