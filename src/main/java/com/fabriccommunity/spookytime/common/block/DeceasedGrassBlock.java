package com.fabriccommunity.spookytime.common.block;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import net.minecraft.block.*;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;

import java.util.List;
import java.util.Random;

public class DeceasedGrassBlock extends GrassBlock {
    public DeceasedGrassBlock(Block.Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(BlockView blockView, BlockPos blockPos, BlockState blockState, boolean boolean_1) {
        return false;
    }

    @Override
    public void grow(World world, Random random, BlockPos blockPos, BlockState blockState) {
        BlockPos blockPos_2 = blockPos.up();
        BlockState blockState_2 = SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState();

        label48:
        for(int int_1 = 0; int_1 < 128; ++int_1) {
            BlockPos blockPos_3 = blockPos_2;

            for(int int_2 = 0; int_2 < int_1 / 16; ++int_2) {
                blockPos_3 = blockPos_3.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (world.getBlockState(blockPos_3.down()).getBlock() != this || world.getBlockState(blockPos_3).method_21743(world, blockPos_3)) {
                    continue label48;
                }
            }

            BlockState blockState_3 = world.getBlockState(blockPos_3);
            if (blockState_3.getBlock() == blockState_2.getBlock() && random.nextInt(10) == 0) {
                ((Fertilizable)blockState_2.getBlock()).grow(world, random, blockPos_3, blockState_3);
            }

            if (blockState_3.isAir()) {
                BlockState blockState_5;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?>> list_1 = world.getBiome(blockPos_3).getFlowerFeatures();
                    if (list_1.isEmpty()) {
                        continue;
                    }

                    blockState_5 = ((FlowerFeature)((DecoratedFeatureConfig)((ConfiguredFeature)list_1.get(0)).config).feature.feature).getFlowerToPlace(random, blockPos_3);
                } else {
                    blockState_5 = blockState_2;
                }

                if (blockState_5.canPlaceAt(world, blockPos_3)) {
                    world.setBlockState(blockPos_3, blockState_5, 3);
                }
            }
        }
    }

    private static boolean canSurvive(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        BlockPos blockPos_2 = blockPos_1.up();
        BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_2);
        int int_1 = ChunkLightProvider.method_20049(viewableWorld_1, blockState_1, blockPos_1, blockState_2, blockPos_2, Direction.UP, blockState_2.getLightSubtracted(viewableWorld_1, blockPos_2));
        return int_1 < viewableWorld_1.getMaxLightLevel();
    }

    private static boolean canSpread(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        BlockPos blockPos_2 = blockPos_1.up();
        return canSurvive(blockState_1, viewableWorld_1, blockPos_1) && !viewableWorld_1.getFluidState(blockPos_2).matches(FluidTags.WATER);
    }

    @Override
    public void onScheduledTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        if (!world.isClient) {
            if (!canSurvive(blockState, world, blockPos)) {
                world.setBlockState(blockPos, SpookyBlocks.DECEASED_DIRT.getDefaultState());
            } else {
                if (world.getLightLevel(blockPos.up()) >= 9) {
                    BlockState blockState_2 = this.getDefaultState();

                    for(int int_1 = 0; int_1 < 4; ++int_1) {
                        BlockPos blockPos_2 = blockPos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (world.getBlockState(blockPos_2).getBlock() == SpookyBlocks.DECEASED_DIRT && canSpread(blockState_2, world, blockPos_2)) {
                            world.setBlockState(blockPos_2, blockState_2);
                        }
                    }
                }

            }
        }
    }
}
