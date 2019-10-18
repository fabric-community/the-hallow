package com.fabriccommunity.thehallow.world;

import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

import com.fabriccommunity.thehallow.registry.HallowBlocks;

public class HallowChunkGeneratorConfig extends OverworldChunkGeneratorConfig {
	public HallowChunkGeneratorConfig() {
		defaultBlock = HallowBlocks.TAINTED_STONE.getDefaultState();
		defaultFluid = HallowBlocks.WITCH_WATER_BLOCK.getDefaultState();
	}
}
