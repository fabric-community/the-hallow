package com.fabriccommunity.thehallow.world.feature;

import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class RandomizedWildCropFeature extends Feature<DefaultFeatureConfig> {
	private final RandomBlockSelector choiceSelector;

	public RandomizedWildCropFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> featureConfig, RandomBlockSelector choiceSelector) {
		super(featureConfig);
		this.choiceSelector = choiceSelector;
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
		int numCrop = 0;

		for (int i = 0; i < 64; ++i) {
			BlockPos randomBlockPos = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAir(randomBlockPos) && world.getBlockState(randomBlockPos.down()).getBlock() == HallowBlocks.DECEASED_GRASS_BLOCK) {
				world.setBlockState(randomBlockPos, this.choiceSelector.getSelection(random, randomBlockPos), 2);
				++numCrop;
			}
		}

		return numCrop > 0;
	}

}
