package com.fabriccommunity.spookytime.client;

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

public class SpookyColors {
	public static void init() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
			return provider == null ? -1 : provider.getColor(block, pos, world, layer);
		}, SpookyBlocks.DECEASED_GRASS_BLOCK, SpookyBlocks.EERIE_GRASS, SpookyBlocks.TALL_EERIE_GRASS);
	}
}
