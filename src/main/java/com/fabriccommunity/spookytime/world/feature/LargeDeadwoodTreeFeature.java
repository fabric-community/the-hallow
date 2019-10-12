package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class LargeDeadwoodTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private static final BlockState LOG = SpookyBlocks.DEADWOOD_LOG.getDefaultState();
	private static final BlockState LEAVES = SpookyBlocks.DEADWOOD_LEAVES.getDefaultState();
	
	public LargeDeadwoodTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean emitBlockUpdates) {
		super(function, emitBlockUpdates);
	}
	
	protected static boolean isNaturalDirt(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == SpookyBlocks.DECEASED_DIRT;
		});
	}
	
	protected static boolean isNaturalDirtOrGrass(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == SpookyBlocks.DECEASED_DIRT || block == SpookyBlocks.DECEASED_GRASS_BLOCK;
		});
	}
	
	public boolean generate(Set<BlockPos> posSet, ModifiableTestableWorld world, Random random, BlockPos pos, MutableIntBoundingBox bb) {
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
				int g = height - random.nextInt(4);
				int h = 2 - random.nextInt(3);
				int startX = x;
				int startZ = z;
				int startY = y + height - 1;
				
				int i;
				int j;
				for (i = 0; i < height; ++i) {
					if (i >= g && h > 0) {
						startX += dir.getOffsetX();
						startZ += dir.getOffsetZ();
						--h;
					}
					
					j = y + i;
					BlockPos trunkPos = new BlockPos(startX, j, startZ);
					if (isAirOrLeaves(world, trunkPos)) {
						this.addLog(posSet, world, trunkPos, bb);
						this.addLog(posSet, world, trunkPos.east(), bb);
						this.addLog(posSet, world, trunkPos.south(), bb);
						this.addLog(posSet, world, trunkPos.east().south(), bb);
					}
				}
				
				for (i = -2; i <= 0; ++i) {
					for (j = -2; j <= 0; ++j) {
						int k = -1;
						this.addLeaves(world, startX + i, startY + k, startZ + j, bb, posSet);
						this.addLeaves(world, 1 + startX - i, startY + k, startZ + j, bb, posSet);
						this.addLeaves(world, startX + i, startY + k, 1 + startZ - j, bb, posSet);
						this.addLeaves(world, 1 + startX - i, startY + k, 1 + startZ - j, bb, posSet);
						if ((i > -2 || j > -1) && (i != -1 || j != -2)) {
							k = 1;
							this.addLeaves(world, startX + i, startY + k, startZ + j, bb, posSet);
							this.addLeaves(world, 1 + startX - i, startY + k, startZ + j, bb, posSet);
							this.addLeaves(world, startX + i, startY + k, 1 + startZ - j, bb, posSet);
							this.addLeaves(world, 1 + startX - i, startY + k, 1 + startZ - j, bb, posSet);
						}
					}
				}
				
				if (random.nextBoolean()) {
					this.addLeaves(world, startX, startY + 2, startZ, bb, posSet);
					this.addLeaves(world, startX + 1, startY + 2, startZ, bb, posSet);
					this.addLeaves(world, startX + 1, startY + 2, startZ + 1, bb, posSet);
					this.addLeaves(world, startX, startY + 2, startZ + 1, bb, posSet);
				}
				
				for (i = -3; i <= 4; ++i) {
					for (j = -3; j <= 4; ++j) {
						if ((i != -3 || j != -3) && (i != -3 || j != 4) && (i != 4 || j != -3) && (i != 4 || j != 4) && (Math.abs(i) < 3 || Math.abs(j) < 3)) {
							this.addLeaves(world, startX + i, startY, startZ + j, bb, posSet);
						}
					}
				}
				
				for (i = -1; i <= 2; ++i) {
					for (j = -1; j <= 2; ++j) {
						if ((i < 0 || i > 1 || j < 0 || j > 1) && random.nextInt(3) <= 0) {
							int trunkHeight = random.nextInt(3) + 2;
							
							int k;
							for (k = 0; k < trunkHeight; ++k) {
								this.addLog(posSet, world, new BlockPos(x + i, startY - k - 1, z + j), bb);
							}
							
							int l;
							for (k = -1; k <= 1; ++k) {
								for (l = -1; l <= 1; ++l) {
									this.addLeaves(world, startX + i + k, startY, startZ + j + l, bb, posSet);
								}
							}
							
							for (k = -2; k <= 2; ++k) {
								for (l = -2; l <= 2; ++l) {
									if (Math.abs(k) != 2 || Math.abs(l) != 2) {
										this.addLeaves(world, startX + i + k, startY - 1, startZ + j + l, bb, posSet);
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
		
		for (int i = 0; i <= height + 1; ++i) {
			int j = 1;
			if (i == 0) {
				j = 0;
			}
			
			if (i >= height - 1) {
				j = 2;
			}
			
			for (int k = -j; k <= j; ++k) {
				for (int l = -j; l <= j; ++l) {
					if (!canTreeReplace(world, mutablePos.set(x + k, y + i, z + l))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void addLog(Set<BlockPos> posSet, ModifiableTestableWorld world, BlockPos pos, MutableIntBoundingBox bb) {
		if (canTreeReplace(world, pos)) {
			this.setBlockState(posSet, world, pos, LOG, bb);
		}
		
	}
	
	private void addLeaves(ModifiableTestableWorld world, int x, int y, int z, MutableIntBoundingBox bb, Set<BlockPos> posSet) {
		BlockPos pos = new BlockPos(x, y, z);
		if (isAir(world, pos)) {
			this.setBlockState(posSet, world, pos, LEAVES, bb);
		}
		
	}
	
	@Override
	protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
		if (!isNaturalDirt(world, pos)) {
			this.setBlockState(world, pos, SpookyBlocks.DECEASED_DIRT.getDefaultState());
		}
	}
}
