package com.fabriccommunity.thehallow.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockRenderLayer;

public class TranslucentGlassBlock extends AbstractGlassBlock {
	public TranslucentGlassBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
