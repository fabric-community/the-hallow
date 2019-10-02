package com.fabriccommunity.spookytime.doomtree;

import com.fabriccommunity.spookytime.doomtree.treeheart.DoomTreeTracker;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DoomedLogBlock extends PillarBlock {

	public DoomedLogBlock(Settings settings) {
		super(settings);
	}


	@Override
	public void onBlockRemoved(BlockState myState, World world, BlockPos blockPos, BlockState newState, boolean someFlag) {
		super.onBlockRemoved(myState, world, blockPos, newState, someFlag);
		DoomTreeTracker.reportBreak(world, blockPos, false);
	}
}
