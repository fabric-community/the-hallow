package com.fabriccommunity.spookytime.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockRenderLayer;

public class TranslucentGlassBlock extends AbstractGlassBlock {
	public TranslucentGlassBlock(Settings block$Settings_1) {
		super(block$Settings_1);
	}
	
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
