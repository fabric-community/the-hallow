package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import java.util.Random;
import java.util.function.Function;

public class SpookyCactusFeature extends Feature<DefaultFeatureConfig> {
	public SpookyCactusFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}
	
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		for (int i = 0; i < 10; i++) {
			BlockPos pos2 = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAir(pos2)) {
				int height = 1 + random.nextInt(random.nextInt(3) + 1);
				
				for (int j = 0; j < height; j++) {
					if (SpookyBlocks.SPOOKY_CACTUS.getDefaultState().canPlaceAt(world, pos2)) {
						world.setBlockState(pos2.up(j), SpookyBlocks.SPOOKY_CACTUS.getDefaultState(), 2);
					}
				}
			}
		}
		
		return true;
	}
}
