package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.LakeDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.DoublePlantFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.LakeFeatureConfig;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;

import com.google.common.collect.Lists;

public class SpookyBiomeFeatures {
	public static void addDefaultSpookyTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.SMALL_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(2, 0.2F, 2) : new CountExtraChanceDecoratorConfig(0, 0.1F, 2)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.LARGE_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(0, 0.5F, 1) : new CountExtraChanceDecoratorConfig(0, 0.01F, 1)));
		//Still needs work
	}
	
	public static void addSpookyForestTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.LARGE_DEADWOOD_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.05F, 1)));
	}
	
	public static void addSpookySwampTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.SMALL_DEADWOOD_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.1F, 1)));
		// Still needs work as well
	}
	
	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}
	
	public static void addDefaultUplandsGeneration(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(SpookyFeatures.STONE_CIRCLE, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(160)));
	}
	
	public static void addLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(4)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(40)));
	}
	
	public static void addExtraLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(2)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(20)));
	}
	
	public static void addWells(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(SpookyFeatures.WITCH_WELL, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(300)));
	}
	
	public static void addLairs(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(SpookyFeatures.SPIDER_LAIR, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(230)));
	}
	
	public static void addBarrows(Biome biome) {
		biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Biome.configureFeature(SpookyFeatures.BARROW, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.35f, 1)));
	}
	
	public static void addMineables(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(SpookyFeatures.ORE, new SpookyOreFeatureConfig(SpookyBlocks.DECEASED_DIRT.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(10, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(SpookyFeatures.ORE, new SpookyOreFeatureConfig(SpookyBlocks.TAINTED_GRAVEL.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(8, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(SpookyFeatures.ORE, new SpookyOreFeatureConfig(SpookyBlocks.INFESTED_TAINTED_STONE.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(5, 0, 0, 256)));
	}
	
	public static void addOres(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(SpookyFeatures.ORE, new SpookyOreFeatureConfig(SpookyBlocks.SPOOKIUM_ORE.getDefaultState(), 5), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 16)));
	}
	
	public static void addGrass(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(SpookyBlocks.EERIE_GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DOUBLE_PLANT, new DoublePlantFeatureConfig(SpookyBlocks.TALL_EERIE_GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addGloomshrooms(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(SpookyBlocks.GLOOMSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addDecoration(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.BRAMBLES, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)));
	}
}
