package com.fabriccommunity.thehallow.world.feature;

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

import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.fabriccommunity.thehallow.registry.HallowFeatures;
import com.fabriccommunity.thehallow.world.biome.HallowForestBiome;

import com.google.common.collect.Lists;

public class HallowBiomeFeatures {
	public static void addDefaultHallowTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.SMALL_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof HallowForestBiome) ? new CountExtraChanceDecoratorConfig(2, 0.2F, 2) : new CountExtraChanceDecoratorConfig(0, 0.1F, 2)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.LARGE_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof HallowForestBiome) ? new CountExtraChanceDecoratorConfig(0, 0.5F, 1) : new CountExtraChanceDecoratorConfig(0, 0.01F, 1)));
		//Still needs work
	}
	
	public static void addHallowForestTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.LARGE_DEADWOOD_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.05F, 1)));
	}
	
	public static void addHallowSwampTrees(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.SMALL_DEADWOOD_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.1F, 1)));
		// Still needs work as well
	}
	
	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(HallowBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(HallowBlocks.DECEASED_DIRT.getDefaultState(), HallowBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(HallowBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(HallowBlocks.DECEASED_DIRT.getDefaultState(), HallowBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}
	
	public static void addDefaultUplandsGeneration(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(HallowFeatures.STONE_CIRCLE, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(160)));
	}
	
	public static void addLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(HallowBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(4)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(HallowBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(40)));
	}
	
	public static void addExtraLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(HallowBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(2)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(HallowBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(20)));
	}
	
	public static void addWells(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(HallowFeatures.WITCH_WELL, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(300)));
	}
	
	public static void addLairs(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(HallowFeatures.SPIDER_LAIR, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(230)));
	}
	
	public static void addBarrows(Biome biome) {
		biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Biome.configureFeature(HallowFeatures.BARROW, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.35f, 1)));
	}
	
	public static void addMineables(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(HallowFeatures.ORE, new HallowOreFeatureConfig(HallowBlocks.DECEASED_DIRT.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(10, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(HallowFeatures.ORE, new HallowOreFeatureConfig(HallowBlocks.TAINTED_GRAVEL.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(8, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(HallowFeatures.ORE, new HallowOreFeatureConfig(HallowBlocks.INFESTED_TAINTED_STONE.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(5, 0, 0, 256)));
	}
	
	public static void addOres(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(HallowFeatures.ORE, new HallowOreFeatureConfig(HallowBlocks.SPOOKIUM_ORE.getDefaultState(), 5), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 16)));
	}
	
	public static void addGrass(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(HallowBlocks.EERIE_GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DOUBLE_PLANT, new DoublePlantFeatureConfig(HallowBlocks.TALL_EERIE_GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addGloomshrooms(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(HallowBlocks.GLOOMSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addDecoration(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.BRAMBLES, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)));
	}
}
