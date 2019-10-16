package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmallDeadwoodTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private static final BlockState LOG = SpookyBlocks.DEADWOOD_LOG.getDefaultState();
	private static final BlockState LEAVES = SpookyBlocks.DEADWOOD_LEAVES.getDefaultState();
	
	public SmallDeadwoodTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean emitBlockUpdates) {
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
		int height = random.nextInt(4) + 5;
		pos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, pos);
		boolean bool = true;
		if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256) {
			int genY;
			int genX;
			int genZ;
			for (genY = pos.getY(); genY <= pos.getY() + 1 + height; ++genY) {
				int h = 1;
				if (genY == pos.getY()) {
					h = 0;
				}
				
				if (genY >= pos.getY() + 1 + height - 2) {
					h = 3;
				}
				
				BlockPos.Mutable mutablePos = new BlockPos.Mutable();
				
				for (genX = pos.getX() - h; genX <= pos.getX() + h && bool; ++genX) {
					for (genZ = pos.getZ() - h; genZ <= pos.getZ() + h && bool; ++genZ) {
						if (genY >= 0 && genY < 256) {
							mutablePos.set(genX, genY, genZ);
							if (!isAirOrLeaves(world, mutablePos)) {
								if (isWater(world, mutablePos)) {
									if (genY > pos.getY()) {
										bool = false;
									}
								} else {
									bool = false;
								}
							}
						} else {
							bool = false;
						}
					}
				}
			}
			
			if (!bool) {
				return false;
			} else if (isNaturalDirtOrGrass(world, pos.down()) && pos.getY() < 256 - height - 1) {
				this.setToDirt(world, pos.down());
				
				int curZ;
				BlockPos genPos;
				int localY;
				int leafSize;
				for (genY = pos.getY() - 3 + height; genY <= pos.getY() + height; ++genY) {
					localY = genY - (pos.getY() + height);
					leafSize = 2 - localY / 2;
					
					for (genX = pos.getX() - leafSize; genX <= pos.getX() + leafSize; ++genX) {
						genZ = genX - pos.getX();
						
						for (curZ = pos.getZ() - leafSize; curZ <= pos.getZ() + leafSize; ++curZ) {
							int offsetZ = curZ - pos.getZ();
							if (Math.abs(genZ) != leafSize || Math.abs(offsetZ) != leafSize || random.nextInt(2) != 0 && localY != 0) {
								genPos = new BlockPos(genX, genY, curZ);
								if (isAirOrLeaves(world, genPos) || isReplaceablePlant(world, genPos)) {
									this.setBlockState(posSet, world, genPos, LEAVES, bb);
								}
							}
						}
					}
				}
				
				for (genY = 0; genY < height; ++genY) {
					BlockPos upPos = pos.up(genY);
					if (isAirOrLeaves(world, upPos) || isWater(world, upPos)) {
						this.setBlockState(posSet, world, upPos, LOG, bb);
					}
				}
				
				for (genY = pos.getY() - 3 + height; genY <= pos.getY() + height; ++genY) {
					localY = genY - (pos.getY() + height);
					leafSize = 2 - localY / 2;
					BlockPos.Mutable mutablePos2 = new BlockPos.Mutable();
					
					for (genZ = pos.getX() - leafSize; genZ <= pos.getX() + leafSize; ++genZ) {
						for (curZ = pos.getZ() - leafSize; curZ <= pos.getZ() + leafSize; ++curZ) {
							mutablePos2.set(genZ, genY, curZ);
							if (isLeaves(world, mutablePos2)) {
								BlockPos vinePosWest = mutablePos2.west();
								BlockPos vinePosEast = mutablePos2.east();
								BlockPos vinePosNorth = mutablePos2.north();
								BlockPos vinePosSouth = mutablePos2.south();
								if (random.nextInt(4) == 0 && isAir(world, vinePosWest)) {
									this.makeVines(world, vinePosWest, VineBlock.EAST);
								}
								
								if (random.nextInt(4) == 0 && isAir(world, vinePosEast)) {
									this.makeVines(world, vinePosEast, VineBlock.WEST);
								}
								
								if (random.nextInt(4) == 0 && isAir(world, vinePosNorth)) {
									this.makeVines(world, vinePosNorth, VineBlock.SOUTH);
								}
								
								if (random.nextInt(4) == 0 && isAir(world, vinePosSouth)) {
									this.makeVines(world, vinePosSouth, VineBlock.NORTH);
								}
							}
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void makeVines(ModifiableTestableWorld world, BlockPos pos, BooleanProperty boolProp) {
		BlockState state = SpookyBlocks.DEADWOOD_VINES.getDefaultState().with(boolProp, true);
		this.setBlockState(world, pos, state);
		int yOffset = 4;
		
		for (pos = pos.down(); isAir(world, pos) && yOffset > 0; --yOffset) {
			this.setBlockState(world, pos, state);
			pos = pos.down();
		}
	}
	
	@Override
	protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
		if (!isNaturalDirt(world, pos)) {
			this.setBlockState(world, pos, SpookyBlocks.DECEASED_DIRT.getDefaultState());
		}
	}
}
