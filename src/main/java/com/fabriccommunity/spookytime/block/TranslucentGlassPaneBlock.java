package com.fabriccommunity.spookytime.block;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.PaneBlock;

public class TranslucentGlassPaneBlock extends PaneBlock {
	public TranslucentGlassPaneBlock(Settings block$Settings_1) {
		super(block$Settings_1);
	}
	
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
