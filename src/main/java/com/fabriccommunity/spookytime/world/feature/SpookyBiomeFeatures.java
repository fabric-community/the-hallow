package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.LakeDecoratorConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.LakeFeatureConfig;

public class SpookyBiomeFeatures {

	public static void addDefaultSpookyTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.SMALL_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(2, 0.2F, 2) : new CountExtraChanceDecoratorConfig(0, 0.1F, 2)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.LARGE_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(0, 0.5F, 1) : new CountExtraChanceDecoratorConfig(0, 0.01F, 1)));
		//Still needs work
	}

	public static void addSpookyForestTrees(Biome biome) {
		if (biome instanceof SpookyForestBiome) {
			biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DARK_OAK_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.05F, 1)));
		}
	}

	public static void addSpookySwampTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.SWAMP_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.1F, 1)));
		// Still needs work as well
	}

	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}

	public static void addDefaultUplandsGeneration(Biome biome) {
		// TODO
	}

	public static void addLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(4)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(40)));
	}

	public static void addWells(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(SpookyFeatures.WITCH_WELL, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(300)));
	}

	public static void addBarrows(Biome biome) {
		biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Biome.configureFeature(SpookyFeatures.BARROW, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.35f, 1)));
	}
}
