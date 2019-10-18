package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class HallowStairsBlock extends StairsBlock {
	public HallowStairsBlock(BlockState base, Settings settings) {
		super(base, settings);
	}
	
	public HallowStairsBlock(Block base, Settings settings) {
		this(base.getDefaultState(), settings);
	}
}
