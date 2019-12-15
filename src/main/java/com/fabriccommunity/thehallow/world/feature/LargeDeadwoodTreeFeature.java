package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.MegaTreeFeatureConfig;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class LargeDeadwoodTreeFeature extends AbstractTreeFeature<MegaTreeFeatureConfig> {
	private static final BlockState LOG = HallowedBlocks.DEADWOOD_LOG.getDefaultState();
	private static final BlockState LEAVES = HallowedBlocks.DEADWOOD_LEAVES.getDefaultState();
	
	public LargeDeadwoodTreeFeature(Function<Dynamic<?>, ? extends MegaTreeFeatureConfig> function) {
		super(function);
	}
	
	protected static boolean isNaturalDirt(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == HallowedBlocks.DECEASED_DIRT;
		});
	}
	
	public static boolean isNaturalDirtOrGrass(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == HallowedBlocks.DECEASED_DIRT || block == HallowedBlocks.DECEASED_GRASS_BLOCK;
		});
	}
	
	@Override
	public boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox bb, MegaTreeFeatureConfig config) {
		int height = random.nextInt(3) + random.nextInt(2) + 6;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (y >= 1 && y + height + 1 < 256) {
			BlockPos downPos = pos.down();
			if (!isNaturalDirtOrGrass(world, downPos)) {
				return false;
			} else if (!this.doesTreeFit(world, pos, height)) {
				return false;
			} else {
				this.setToDirt(world, downPos);
				this.setToDirt(world, downPos.east());
				this.setToDirt(world, downPos.south());
				this.setToDirt(world, downPos.south().east());
				Direction dir = Direction.Type.HORIZONTAL.random(random);
				int baseTrunkHeight = height - random.nextInt(4);
				int heightVariance = 2 - random.nextInt(3);
				int startX = x;
				int startZ = z;
				int startY = y + height - 1;
				
				for (int localHeight = 0; localHeight < height; ++localHeight) {
					if (localHeight >= baseTrunkHeight && heightVariance > 0) {
						startX += dir.getOffsetX();
						startZ += dir.getOffsetZ();
						--heightVariance;
					}
					
					int localY = y + localHeight;
					BlockPos trunkPos = new BlockPos(startX, localY, startZ);
					if (isAirOrLeaves(world, trunkPos)) {
						this.addLog(logPositions, world, trunkPos, bb);
						this.addLog(logPositions, world, trunkPos.east(), bb);
						this.addLog(logPositions, world, trunkPos.south(), bb);
						this.addLog(logPositions, world, trunkPos.east().south(), bb);
					}
				}
				
				for (int xOffset = -2; xOffset <= 0; ++xOffset) {
					for (int zOffset = -2; zOffset <= 0; ++zOffset) {
						int yOffset = -1;
						this.addLeaves(world, startX + xOffset, startY + yOffset, startZ + zOffset, bb, leavesPositions);
						this.addLeaves(world, 1 + startX - xOffset, startY + yOffset, startZ + zOffset, bb, leavesPositions);
						this.addLeaves(world, startX + xOffset, startY + yOffset, 1 + startZ - zOffset, bb, leavesPositions);
						this.addLeaves(world, 1 + startX - xOffset, startY + yOffset, 1 + startZ - zOffset, bb, leavesPositions);
						if ((xOffset > -2 || zOffset > -1) && (xOffset != -1 || zOffset != -2)) {
							yOffset = 1;
							this.addLeaves(world, startX + xOffset, startY + yOffset, startZ + zOffset, bb, leavesPositions);
							this.addLeaves(world, 1 + startX - xOffset, startY + yOffset, startZ + zOffset, bb, leavesPositions);
							this.addLeaves(world, startX + xOffset, startY + yOffset, 1 + startZ - zOffset, bb, leavesPositions);
							this.addLeaves(world, 1 + startX - xOffset, startY + yOffset, 1 + startZ - zOffset, bb, leavesPositions);
						}
					}
				}
				
				if (random.nextBoolean()) {
					this.addLeaves(world, startX, startY + 2, startZ, bb, leavesPositions);
					this.addLeaves(world, startX + 1, startY + 2, startZ, bb, leavesPositions);
					this.addLeaves(world, startX + 1, startY + 2, startZ + 1, bb, leavesPositions);
					this.addLeaves(world, startX, startY + 2, startZ + 1, bb, leavesPositions);
				}
				
				for (int xOffset = -3; xOffset <= 4; ++xOffset) {
					for (int zOffset = -3; zOffset <= 4; ++zOffset) {
						if ((xOffset != -3 || zOffset != -3) && (xOffset != -3 || zOffset != 4) && (xOffset != 4 || zOffset != -3) && (xOffset != 4 || zOffset != 4) && (Math.abs(xOffset) < 3 || Math.abs(zOffset) < 3)) {
							this.addLeaves(world, startX + xOffset, startY, startZ + zOffset, bb, leavesPositions);
						}
					}
				}
				
				for (int xOffset = -1; xOffset <= 2; ++xOffset) {
					for (int zOffset = -1; zOffset <= 2; ++zOffset) {
						if ((xOffset < 0 || xOffset > 1 || zOffset < 0 || zOffset > 1) && random.nextInt(3) <= 0) {
							int trunkHeight = random.nextInt(3) + 2;
							
							for (int localY = 0; localY < trunkHeight; ++localY) {
								this.addLog(logPositions, world, new BlockPos(x + xOffset, startY - localY - 1, z + zOffset), bb);
							}
							
							for (int localX = -1; localX <= 1; ++localX) {
								for (int localZ = -1; localZ <= 1; ++localZ) {
									this.addLeaves(world, startX + xOffset + localX, startY, startZ + zOffset + localZ, bb, leavesPositions);
								}
							}
							
							for (int localX = -2; localX <= 2; ++localX) {
								for (int localZ = -2; localZ <= 2; ++localZ) {
									if (Math.abs(localX) != 2 || Math.abs(localZ) != 2) {
										this.addLeaves(world, startX + xOffset + localX, startY - 1, startZ + zOffset + localZ, bb, leavesPositions);
									}
								}
							}
						}
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}
	
	private boolean doesTreeFit(TestableWorld world, BlockPos pos, int height) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		
		for (int localY = 0; localY <= height + 1; ++localY) {
			int leafSize = 1;
			if (localY == 0) {
				leafSize = 0;
			}
			
			if (localY >= height - 1) {
				leafSize = 2;
			}
			
			for (int localX = -leafSize; localX <= leafSize; ++localX) {
				for (int localZ = -leafSize; localZ <= leafSize; ++localZ) {
					if (!canTreeReplace(world, mutablePos.set(x + localX, y + localY, z + localZ))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void addLog(Set<BlockPos> posSet, ModifiableTestableWorld world, BlockPos pos, BlockBox bb) {
		if (canTreeReplace(world, pos)) {
			this.setBlockState(world, pos, LOG, bb);
			posSet.add(pos.toImmutable());
		}
		
	}
	
	private void addLeaves(ModifiableTestableWorld world, int x, int y, int z, BlockBox bb, Set<BlockPos> posSet) {
		BlockPos pos = new BlockPos(x, y, z);
		if (isAir(world, pos)) {
			this.setBlockState(world, pos, LEAVES, bb);
			posSet.add(pos.toImmutable());
		}
		
	}
	
	@Override
	protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
		if (!isNaturalDirt(world, pos)) {
			this.setBlockState(world, pos, HallowedBlocks.DECEASED_DIRT.getDefaultState());
		}
	}
}
