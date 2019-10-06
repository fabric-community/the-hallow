package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFluidTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class BleedingBlock extends Block {

	public BleedingBlock(Settings settings) {
		super(settings);
	}

	public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		WitchWaterBubbleColumnBlock.update(world_1, blockPos_1.up(), true);
	}

	public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
		if (direction_1 == Direction.UP && blockState_2.getBlock() == SpookyBlocks.WITCH_WATER_BLOCK) {
			iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(iWorld_1));
		}

		return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
	}

	public void onRandomTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		BlockPos blockPos_2 = blockPos_1.up();
		if (world_1.getFluidState(blockPos_1).matches(SpookyFluidTags.WITCH_WATER)) {
			world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.ENTITY_DROWNED_HURT_WATER, SoundCategory.BLOCKS, 0.5F, 2.6F + (world_1.random.nextFloat() - world_1.random.nextFloat()) * 0.8F);
			if (world_1 instanceof ServerWorld) {
				((ServerWorld)world_1).spawnParticles(ParticleTypes.CURRENT_DOWN, (double)blockPos_2.getX() + 0.5D, (double)blockPos_2.getY() + 0.25D, (double)blockPos_2.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
			}
		}

	}

	public int getTickRate(ViewableWorld viewableWorld_1) {
		return 20;
	}

	public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
		world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
	}
}
