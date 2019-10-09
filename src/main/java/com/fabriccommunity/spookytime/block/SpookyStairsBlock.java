package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class SpookyStairsBlock extends StairsBlock {
	public SpookyStairsBlock(BlockState base, Settings settings) {
		super(base, settings);
	}

	public SpookyStairsBlock(Block base, Settings settings) {
		this(base.getDefaultState(), settings);
	}
}
