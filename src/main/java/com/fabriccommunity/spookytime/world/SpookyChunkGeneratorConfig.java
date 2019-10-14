package com.fabriccommunity.spookytime.world;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

public class SpookyChunkGeneratorConfig extends OverworldChunkGeneratorConfig {
	public SpookyChunkGeneratorConfig() {
		defaultBlock = SpookyBlocks.TAINTED_STONE.getDefaultState();
		defaultFluid = SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
	}
}
