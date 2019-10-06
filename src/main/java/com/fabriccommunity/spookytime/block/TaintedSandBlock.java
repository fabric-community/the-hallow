package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class TaintedSandBlock extends Block{

	public TaintedSandBlock(Block.Settings settings) {
		super(settings);
	}

	public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		WitchWaterBubbleColumnBlock.update(world_1, blockPos_1.up(), false);
	}

	public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
		world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
	}

	public int getTickRate(ViewableWorld viewableWorld_1) {
		return 20;
	}

	public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
		world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
	}
}
