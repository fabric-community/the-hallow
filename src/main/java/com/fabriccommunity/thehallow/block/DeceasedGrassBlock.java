package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.GrassBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.List;
import java.util.Random;

public class DeceasedGrassBlock extends GrassBlock {
	public DeceasedGrassBlock(Block.Settings settings) {
		super(settings);
	}
	
	private static boolean canSurvive(BlockState blockState, WorldView viewableWorld, BlockPos blockPos) {
		BlockPos upPos = blockPos.up();
		BlockState upBlockState = viewableWorld.getBlockState(upPos);
		int lightLevel = ChunkLightProvider.getRealisticOpacity(viewableWorld, blockState, blockPos, upBlockState, upPos, Direction.UP, upBlockState.getOpacity(viewableWorld, upPos));
		return lightLevel < viewableWorld.getMaxLightLevel();
	}
	
	private static boolean canSpread(BlockState blockState, WorldView viewableWorld, BlockPos blockPos) {
		BlockPos upPos = blockPos.up();
		return canSurvive(blockState, viewableWorld, blockPos) && !viewableWorld.getFluidState(upPos).matches(FluidTags.WATER);
	}
	
	@Override
	public boolean isFertilizable(BlockView blockView, BlockPos blockPos, BlockState blockState, boolean boolean_1) {
		return false;
	}
	
	@Override
	public void grow(ServerWorld world, Random random, BlockPos blockPos, BlockState blockState) {
		BlockPos upPos = blockPos.up();
		BlockState grassBlock = HallowedBlocks.DECEASED_GRASS_BLOCK.getDefaultState();
		
		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos randomPos = upPos;
			
			for (int j = 0; j < i / 16; ++j) {
				randomPos = randomPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (world.getBlockState(randomPos.down()).getBlock() != this || world.getBlockState(randomPos).isFullCube(world, randomPos)) {
					continue label48;
				}
			}
			
			BlockState randomBlockState = world.getBlockState(randomPos);
			if (randomBlockState.getBlock() == grassBlock.getBlock() && random.nextInt(10) == 0) {
				((Fertilizable) grassBlock.getBlock()).grow(world, random, randomPos, randomBlockState);
			}
			
			if (randomBlockState.isAir()) {
				BlockState stateToPlace;
				if (random.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = world.getBiomeAccess().getBiome(randomPos).getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}
					stateToPlace = ((FlowerFeature) ((DecoratedFeatureConfig) (list.get(0)).config).feature.feature).getFlowerToPlace(random, randomPos, list.get(0).config);
				} else {
					stateToPlace = grassBlock;
				}
				
				if (stateToPlace.canPlaceAt(world, randomPos)) {
					world.setBlockState(randomPos, stateToPlace, 3);
				}
			}
		}
	}
	
	@Override
	public void scheduledTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
		if (!world.isClient) {
			if (!canSurvive(blockState, world, blockPos)) {
				world.setBlockState(blockPos, HallowedBlocks.DECEASED_DIRT.getDefaultState());
			} else {
				if (world.getLightLevel(blockPos.up()) >= 9) {
					BlockState defaultState = this.getDefaultState();
					
					for (int i = 0; i < 4; ++i) {
						BlockPos randomPos = blockPos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (world.getBlockState(randomPos).getBlock() == HallowedBlocks.DECEASED_DIRT && canSpread(defaultState, world, randomPos)) {
							world.setBlockState(randomPos, defaultState);
						}
					}
				}
			}
		}
	}
}
