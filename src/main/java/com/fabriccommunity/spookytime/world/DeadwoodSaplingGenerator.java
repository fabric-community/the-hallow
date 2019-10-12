package com.fabriccommunity.spookytime.world;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import com.fabriccommunity.spookytime.world.feature.LargeDeadwoodTreeFeature;
import com.fabriccommunity.spookytime.world.feature.SmallDeadwoodTreeFeature;

import java.util.Random;

public class DeadwoodSaplingGenerator extends LargeTreeSaplingGenerator {
	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random rand) {
		return new SmallDeadwoodTreeFeature(DefaultFeatureConfig::deserialize, true);
	}
	
	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createLargeTreeFeature(Random var1) {
		return new LargeDeadwoodTreeFeature(DefaultFeatureConfig::deserialize, true);
	}
}
