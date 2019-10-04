package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.biome.SpookyForestBiome;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.LakeDecoratorConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.LakeFeatureConfig;

public class SpookyBiomeFeatures {
    public static void addSpookyForestTrees(Biome biome) {
		if (biome instanceof SpookyForestBiome) {
			biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DARK_OAK_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.05F, 1)));
		}
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.SMALL_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(2, 0.2F, 2) : new CountExtraChanceDecoratorConfig(0, 0.1F, 2)));
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.LARGE_SKELETON_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, (biome instanceof SpookyForestBiome) ? new CountExtraChanceDecoratorConfig(0, 0.5F, 1) : new CountExtraChanceDecoratorConfig(0, 0.01F, 1)));
		//Still needs work
	}
	
	public static void addDisks(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_SAND.getDefaultState(), 7, 2, Lists.newArrayList(new BlockState[]{SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState()})), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(3)));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.DISK, new DiskFeatureConfig(SpookyBlocks.TAINTED_GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(new BlockState[]{SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState()})), Decorator.COUNT_TOP_SOLID, new CountDecoratorConfig(1)));
	}

	public static void addLakes(Biome biome) {
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.LAKE, new LakeFeatureConfig(SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(4)));
		biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.LAKE, new LakeFeatureConfig(Blocks.LAVA.getDefaultState()), Decorator.LAVA_LAKE, new LakeDecoratorConfig(80)));
	}
}
