package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class HallowedStairsBlock extends StairsBlock {
	public HallowedStairsBlock(BlockState base, Settings settings) {
		super(base, settings);
	}
	
	public HallowedStairsBlock(Block base, Settings settings) {
		this(base.getDefaultState(), settings);
	}
}
