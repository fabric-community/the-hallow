package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class CoarseTaintedSandBlock extends FallingBlock {
	public CoarseTaintedSandBlock(Block.Settings settings) {
		super(settings);
	}
	
	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		WitchWaterBubbleColumnBlock.update(world, pos.up(), false);
		super.scheduledTick(state, world, pos, rand);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool) {
		world.getBlockTickScheduler().schedule(pos, this, this.getTickRate(world));
	}
	
	@Override
	public int getTickRate(WorldView viewableworld) {
		return 2;
	}
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState state2, boolean bool) {
		world.getBlockTickScheduler().schedule(pos, this, this.getTickRate(world));
	}
}
