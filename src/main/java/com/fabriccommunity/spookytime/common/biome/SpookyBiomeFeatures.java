package com.fabriccommunity.spookytime.common.biome;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SpookyBiomeFeatures {
    public static TernarySurfaceConfig SPOOKY_FOREST = new TernarySurfaceConfig(SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState(), SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.DECEASED_GRAVEL.getDefaultState());

	public static void addSpookyForestTrees(Biome biome) {

    }
}