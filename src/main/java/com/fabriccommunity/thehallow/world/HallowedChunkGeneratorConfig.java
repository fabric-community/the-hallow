package com.fabriccommunity.thehallow.world;

import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

public class HallowedChunkGeneratorConfig extends OverworldChunkGeneratorConfig {
	public HallowedChunkGeneratorConfig() {
		defaultBlock = HallowedBlocks.TAINTED_STONE.getDefaultState();
		defaultFluid = HallowedBlocks.WITCH_WATER_BLOCK.getDefaultState();
	}
}
