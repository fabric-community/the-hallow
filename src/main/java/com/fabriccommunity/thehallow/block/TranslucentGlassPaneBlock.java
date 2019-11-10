package com.fabriccommunity.thehallow.block;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.PaneBlock;

public class TranslucentGlassPaneBlock extends PaneBlock {
	public TranslucentGlassPaneBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
