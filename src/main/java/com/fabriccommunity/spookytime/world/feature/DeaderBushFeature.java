package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
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

public class DeaderBushFeature extends Feature<DefaultFeatureConfig> {
	private final BlockState state;
	
	public DeaderBushFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, BlockState state) {
		super(function_1);
		this.state = state;
	}
	
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		for (BlockState state1 = world.getBlockState(pos); (state1.isAir() || state1.matches(BlockTags.LEAVES)) && pos.getY() > 0; state1 = world.getBlockState(pos)) {
			pos = pos.down();
		}
		
		for (int int_1 = 0; int_1 < 4; ++int_1) {
			BlockPos pos2 = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAir(pos2) && state.canPlaceAt(world, pos2)) {
				world.setBlockState(pos2, state, 2);
			}
		}
		
		return true;
	}
}
