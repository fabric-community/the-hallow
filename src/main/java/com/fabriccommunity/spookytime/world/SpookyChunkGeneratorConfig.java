package com.fabriccommunity.spookytime.world;

import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

public class SpookyChunkGeneratorConfig extends OverworldChunkGeneratorConfig {
	public SpookyChunkGeneratorConfig() {
		defaultBlock = SpookyBlocks.TAINTED_STONE.getDefaultState();
		defaultFluid = SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
	}
}
