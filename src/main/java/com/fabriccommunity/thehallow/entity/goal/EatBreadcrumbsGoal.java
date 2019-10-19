package com.fabriccommunity.thehallow.entity.goal;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.Random;

public class EatBreadcrumbsGoal extends MoveToTargetPosGoal {
	private final Block targetBlock;
	private final MobEntity stepAndDestroyMob;
	private int counter;
	
	public EatBreadcrumbsGoal(Block block, MobEntityWithAi mobEntityWithAi, double speed, int hRange, int vRange) {
		super(mobEntityWithAi, speed, hRange, vRange);
		this.targetBlock = block;
		this.stepAndDestroyMob = mobEntityWithAi;
	}
	
	@Override
	public boolean canStart() {
		if (!this.stepAndDestroyMob.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
			return false;
		} else if (this.cooldown > 0) {
			--this.cooldown;
			return false;
		} else if (this.hasAvailableTarget()) {
			this.cooldown = 20;
			return true;
		} else {
			this.cooldown = this.getInterval(this.mob);
			return false;
		}
	}
	
	private boolean hasAvailableTarget() {
		return this.targetPos != null && this.isTargetPos(this.mob.world, this.targetPos) || this.findTargetPos();
	}
	
	@Override
	public void stop() {
		super.stop();
	}
	
	@Override
	public void start() {
		super.start();
		this.counter = 0;
	}
	
	public void tickStepping(IWorld iWorld, BlockPos blockPos) {
	}
	
	public void onDestroyBlock(World world, BlockPos blockPos) {
	}
	
	@Override
	public void tick() {
		super.tick();
		World world = this.stepAndDestroyMob.world;
		BlockPos blockPos = new BlockPos(this.stepAndDestroyMob);
		BlockPos blockPos2 = this.tweakToProperPos(blockPos, world);
		Random random = this.stepAndDestroyMob.getRand();
		if (this.hasReached() && blockPos2 != null) {
			Vec3d vec3d2;
			double double2;
			if (this.counter > 0) {
				vec3d2 = this.stepAndDestroyMob.getVelocity();
				this.stepAndDestroyMob.setVelocity(vec3d2.x, 0.3D, vec3d2.z);
				if (!world.isClient) {
					double2 = 0.08D;
					((ServerWorld) world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(HallowedBlocks.BREAD_CRUMBS)), (double) blockPos2.getX() + 0.5D, (double) blockPos2.getY() + 0.7D, (double) blockPos2.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, 0.15000000596046448D);
				}
			}
			
			if (this.counter % 2 == 0) {
				vec3d2 = this.stepAndDestroyMob.getVelocity();
				this.stepAndDestroyMob.setVelocity(vec3d2.x, -0.3D, vec3d2.z);
				if (this.counter % 6 == 0) {
					this.tickStepping(world, this.targetPos);
				}
			}
			
			if (this.counter > 60) {
				world.clearBlockState(blockPos2, false);
				if (!world.isClient) {
					for (int i = 0; i < 20; ++i) {
						double2 = random.nextGaussian() * 0.02D;
						double double3 = random.nextGaussian() * 0.02D;
						double double4 = random.nextGaussian() * 0.02D;
						((ServerWorld) world).spawnParticles(ParticleTypes.POOF, (double) blockPos2.getX() + 0.5D, blockPos2.getY(), (double) blockPos2.getZ() + 0.5D, 1, double2, double3, double4, 0.15000000596046448D);
					}
					
					this.onDestroyBlock(world, blockPos2);
				}
			}
			
			++this.counter;
		}
		
	}
	
	private BlockPos tweakToProperPos(BlockPos pos, BlockView blockView) {
		if (blockView.getBlockState(pos).getBlock() == this.targetBlock) {
			return pos;
		} else {
			BlockPos[] blockPoss = new BlockPos[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()};
			BlockPos[] var4 = blockPoss;
			int var5 = blockPoss.length;
			
			for (int var6 = 0; var6 < var5; ++var6) {
				BlockPos blockPos2 = var4[var6];
				if (blockView.getBlockState(blockPos2).getBlock() == this.targetBlock) {
					return blockPos2;
				}
			}
			
			return null;
		}
	}
	
	@Override
	protected boolean isTargetPos(ViewableWorld world, BlockPos pos) {
		Chunk chunk1 = world.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
		if (chunk1 == null) {
			return false;
		} else {
			return chunk1.getBlockState(pos).getBlock() == this.targetBlock && chunk1.getBlockState(pos.up()).isAir() && chunk1.getBlockState(pos.up(2)).isAir();
		}
	}
}
