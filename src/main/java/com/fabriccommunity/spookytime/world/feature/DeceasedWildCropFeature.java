package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class DeceasedWildCropFeature extends Feature<DefaultFeatureConfig> {
    protected final BlockState crop;

    public DeceasedWildCropFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> featureConfig, BlockState blockState_1) {
        super(featureConfig);
        this.crop = blockState_1;
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
        int numCrop = 0;

        for(int i = 0; i < 64; ++i) {
            BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (world.isAir(blockPos2) && world.getBlockState(blockPos2.down()).getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK) {
                world.setBlockState(blockPos2, this.crop, 2);
                ++numCrop;
            }
        }

        return numCrop > 0;
    }
}
