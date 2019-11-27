package com.fabriccommunity.thehallow.world.feature;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedFeatures;
import com.fabriccommunity.thehallow.world.biome.HallowedForestBiome;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.LakeDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;
import net.minecraft.world.gen.feature.MegaTreeFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleStateProvider;

public class HallowedBiomeFeatures {
	
	public static <F extends FeatureConfig, D extends DecoratorConfig> ConfiguredFeature<?, ?> configureFeature(Feature<F> feature, F featureConfig, Decorator<D> decorator, D decoratorConfig) {
		Feature<DecoratedFeatureConfig> feature2 = feature instanceof FlowerFeature ? Feature.DECORATED_FLOWER : Feature.DECORATED;
		return new ConfiguredFeature(feature2, new DecoratedFeatureConfig(feature.configure(featureConfig), decorator.configure(decoratorConfig)));
	}
	
	public static void addDefaultHallowedTrees(Biome biome) {
		//note: the feature config here is completely irrelevant. The feature ignores it. I've tried to make it at least somewhat similar in case someone does something strange with it.
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.SMALL_SKELETON_TREE, new TreeFeatureConfig.Builder(new SimpleStateProvider(Blocks.BONE_BLOCK.getDefaultState()), new SimpleStateProvider(Blocks.AIR.getDefaultState())).build(), Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof HallowedForestBiome) ? new CountExtraChanceDecoratorConfig(2, 0.2F, 2) : new CountExtraChanceDecoratorConfig(0, 0.1F, 2)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.LARGE_SKELETON_TREE, new TreeFeatureConfig.Builder(new SimpleStateProvider(Blocks.BONE_BLOCK.getDefaultState()), new SimpleStateProvider(Blocks.AIR.getDefaultState())).build(), Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof HallowedForestBiome) ? new CountExtraChanceDecoratorConfig(0, 0.5F, 1) : new CountExtraChanceDecoratorConfig(0, 0.01F, 1)));
		//Still needs work
	}
	
	public static void addHallowedForestTrees(Biome biome) {
		//note: the feature config here is completely irrelevant. The feature ignores it. I've tried to make it at least somewhat similar in case someone does something strange with it.
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.LARGE_DEADWOOD_TREE, new MegaTreeFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.DEADWOOD_LOG.getDefaultState()), new SimpleStateProvider(HallowedBlocks.DEADWOOD_LEAVES.getDefaultState())).baseHeight(6).build(), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.05F, 1)));
	}
	
	public static void addHallowedSwampTrees(Biome biome) {
		//note: the feature config here is completely irrelevant. The feature ignores it. I've tried to make it at least somewhat similar in case someone does something strange with it.
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.SMALL_DEADWOOD_TREE, new BranchedTreeFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.DEADWOOD_LOG.getDefaultState()), new SimpleStateProvider(HallowedBlocks.DEADWOOD_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0)).baseHeight(4).heightRandA(2).foliageHeight(3).noVines().build(), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.1F, 1)));
		// Still needs work as well
	}
	
	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(HallowedBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(HallowedBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.DECEASED_GRASS_BLOCK.getDefaultState())), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}
	
	public static void addDefaultUplandsGeneration(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, configureFeature(HallowedFeatures.STONE_CIRCLE, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new LakeDecoratorConfig(160)));
	}
	
	public static void addLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, configureFeature(Feature.LAKE, new BushFeatureConfig(HallowedBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(4)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, configureFeature(Feature.LAKE, new BushFeatureConfig(HallowedBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(40)));
	}
	
	public static void addExtraLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, configureFeature(Feature.LAKE, new BushFeatureConfig(HallowedBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(2)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, configureFeature(Feature.LAKE, new BushFeatureConfig(HallowedBlocks.BLOOD_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(20)));
	}
	
	public static void addWells(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, configureFeature(HallowedFeatures.WITCH_WELL, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new LakeDecoratorConfig(300)));
	}
	
	public static void addLairs(Biome biome) {
		biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, configureFeature(HallowedFeatures.SPIDER_LAIR, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new LakeDecoratorConfig(230)));
	}
	
	public static void addBarrows(Biome biome) {
		biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, configureFeature(HallowedFeatures.BARROW, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.35f, 1)));
	}
	
	public static void addMineables(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(HallowedFeatures.ORE, new HallowedOreFeatureConfig(HallowedBlocks.DECEASED_DIRT.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(10, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(HallowedFeatures.ORE, new HallowedOreFeatureConfig(HallowedBlocks.TAINTED_GRAVEL.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(8, 0, 0, 256)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(HallowedFeatures.ORE, new HallowedOreFeatureConfig(HallowedBlocks.INFESTED_TAINTED_STONE.getDefaultState(), 33), Decorator.COUNT_RANGE, new RangeDecoratorConfig(5, 0, 0, 256)));
	}
	
	public static void addOres(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, configureFeature(HallowedFeatures.ORE, new HallowedOreFeatureConfig(HallowedBlocks.HALLOWED_ORE.getDefaultState(), 5), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 16)));
	}
	
	public static void addGrass(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.EERIE_GRASS.getDefaultState()), new SimpleBlockPlacer()).tries(32).build(), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.TALL_EERIE_GRASS.getDefaultState()), new SimpleBlockPlacer()).tries(64).cannotProject().build(), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addGloomshrooms(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.GLOOMSHROOM.getDefaultState()), new SimpleBlockPlacer()).tries(32).build(), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
	}
	
	public static void addDecoration(Biome biome) {
		
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.BRAMBLES, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)));
	}
	
	public static void addColoredPumpkins(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(HallowedFeatures.COLORED_PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new LakeDecoratorConfig(32)));
	}
}
