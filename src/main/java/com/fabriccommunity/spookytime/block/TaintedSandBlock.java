package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class TaintedSandBlock extends FallingBlock {

	public TaintedSandBlock(Block.Settings settings) {
		super(settings);
	}

	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random rand) {
		WitchWaterBubbleColumnBlock.update(world, pos.up(), false);
		super.onScheduledTick(state, world, pos, rand);
	}

	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool) {
		world.getBlockTickScheduler().schedule(pos, this, this.getTickRate(world));
	}

	public int getTickRate(ViewableWorld viewableworld) {
		return 2;
	}

	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState blockState_2, boolean bool) {
		world.getBlockTickScheduler().schedule(pos, this, this.getTickRate(world));
	}
}
