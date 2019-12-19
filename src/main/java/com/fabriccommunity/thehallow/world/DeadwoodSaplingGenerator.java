package com.fabriccommunity.thehallow.world;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.MegaTreeFeatureConfig;

import com.fabriccommunity.thehallow.registry.HallowedFeatures;

import java.util.Random;

public class DeadwoodSaplingGenerator extends LargeTreeSaplingGenerator {
	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random rand) {
		return HallowedFeatures.SMALL_DEADWOOD_TREE.configure(null);
	}
	
	@Override
	protected ConfiguredFeature<MegaTreeFeatureConfig, ?> createLargeTreeFeature(Random var1) {
		return HallowedFeatures.LARGE_DEADWOOD_TREE.configure(null);
	}
}
