package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;

import com.fabriccommunity.thehallow.registry.HallowBlocks;

public class HallowColors {
	public static void init() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
			return provider == null ? -1 : provider.getColor(block, pos, world, layer);
		}, HallowBlocks.DECEASED_GRASS_BLOCK, HallowBlocks.EERIE_GRASS, HallowBlocks.TALL_EERIE_GRASS);
	}
}
