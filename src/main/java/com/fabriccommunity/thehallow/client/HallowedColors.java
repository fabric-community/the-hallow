package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

public class HallowedColors {
	public static void init() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
			return provider == null ? -1 : provider.getColor(block, pos, world, layer);
		}, HallowedBlocks.DECEASED_GRASS_BLOCK, HallowedBlocks.EERIE_GRASS, HallowedBlocks.TALL_EERIE_GRASS);
	}
}
