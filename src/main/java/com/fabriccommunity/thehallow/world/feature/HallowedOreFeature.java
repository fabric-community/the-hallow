package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.Feature;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class HallowedOreFeature extends Feature<HallowedOreFeatureConfig> {
	public HallowedOreFeature(Function<Dynamic<?>, ? extends HallowedOreFeatureConfig> function_1) {
		super(function_1);
	}
	
	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, HallowedOreFeatureConfig oreFeatureConfig) {
		float randomNumberFromZeroToPi = random.nextFloat() * 3.1415927F;
		float dividedSize = (float) oreFeatureConfig.size / 8.0F;
		int ceilSize = MathHelper.ceil(((float) oreFeatureConfig.size / 16.0F * 2.0F + 1.0F) / 2.0F);
		double positiveX = (blockPos.getX() + MathHelper.sin(randomNumberFromZeroToPi) * dividedSize);
		double negativeX = (blockPos.getX() - MathHelper.sin(randomNumberFromZeroToPi) * dividedSize);
		double positiveZ = (blockPos.getZ() + MathHelper.cos(randomNumberFromZeroToPi) * dividedSize);
		double negativeZ = (blockPos.getZ() - MathHelper.cos(randomNumberFromZeroToPi) * dividedSize);
		double positiveY = (blockPos.getY() + random.nextInt(3) - 2);
		double negativeY = (blockPos.getY() + random.nextInt(3) - 2);
		int startX = blockPos.getX() - MathHelper.ceil(dividedSize) - ceilSize;
		int y = blockPos.getY() - 2 - ceilSize;
		int startZ = blockPos.getZ() - MathHelper.ceil(dividedSize) - ceilSize;
		int xSize = 2 * (MathHelper.ceil(dividedSize) + ceilSize);
		int int_7 = 2 * (2 + ceilSize);
		
		for (int x = startX; x <= startX + xSize; ++x) {
			for (int z = startZ; z <= startZ + xSize; ++z) {
				if (y <= world.getTopY(Type.OCEAN_FLOOR_WG, x, z)) {
					return this.generateVeinPart(world, random, oreFeatureConfig, positiveX, negativeX, positiveZ, negativeZ, positiveY, negativeY, startX, y, startZ, xSize, int_7);
				}
			}
		}
		
		return false;
	}
	
	protected boolean generateVeinPart(IWorld world, Random random, HallowedOreFeatureConfig oreFeatureConfig, double positiveX, double negativeX, double positiveZ, double negativeZ, double double_5, double double_6, int startX, int yPosition, int startZ, int xSize, int int_5) {
		int stonesPlaced = 0;
		BitSet bitSet = new BitSet(xSize * int_5 * xSize);
		Mutable blockPos = new Mutable();
		double[] doubles_1 = new double[oreFeatureConfig.size * 4];
		
		int counter;
		double currentX;
		double currentY;
		double currentZ;
		double double_15;
		for (counter = 0; counter < oreFeatureConfig.size; ++counter) {
			float progress = (float) counter / (float) oreFeatureConfig.size;
			currentX = MathHelper.lerp(progress, positiveX, negativeX);
			currentY = MathHelper.lerp(progress, double_5, double_6);
			currentZ = MathHelper.lerp(progress, positiveZ, negativeZ);
			double_15 = random.nextDouble() * (double) oreFeatureConfig.size / 16.0D;
			double double_11 = ((double) (MathHelper.sin(3.1415927F * progress) + 1.0F) * double_15 + 1.0D) / 2.0D;
			doubles_1[counter * 4 + 0] = currentX;
			doubles_1[counter * 4 + 1] = currentY;
			doubles_1[counter * 4 + 2] = currentZ;
			doubles_1[counter * 4 + 3] = double_11;
		}
		
		for (counter = 0; counter < oreFeatureConfig.size - 1; ++counter) {
			if (doubles_1[counter * 4 + 3] > 0.0D) {
				for (int int_9 = counter + 1; int_9 < oreFeatureConfig.size; ++int_9) {
					if (doubles_1[int_9 * 4 + 3] > 0.0D) {
						currentX = doubles_1[counter * 4 + 0] - doubles_1[int_9 * 4 + 0];
						currentY = doubles_1[counter * 4 + 1] - doubles_1[int_9 * 4 + 1];
						currentZ = doubles_1[counter * 4 + 2] - doubles_1[int_9 * 4 + 2];
						double_15 = doubles_1[counter * 4 + 3] - doubles_1[int_9 * 4 + 3];
						if (double_15 * double_15 > currentX * currentX + currentY * currentY + currentZ * currentZ) {
							if (double_15 > 0.0D) {
								doubles_1[int_9 * 4 + 3] = -1.0D;
							} else {
								doubles_1[counter * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}
		
		for (counter = 0; counter < oreFeatureConfig.size; ++counter) {
			double double_16 = doubles_1[counter * 4 + 3];
			if (double_16 >= 0.0D) {
				double double_17 = doubles_1[counter * 4 + 0];
				double double_18 = doubles_1[counter * 4 + 1];
				double double_19 = doubles_1[counter * 4 + 2];
				int int_11 = Math.max(MathHelper.floor(double_17 - double_16), startX);
				int int_12 = Math.max(MathHelper.floor(double_18 - double_16), yPosition);
				int int_13 = Math.max(MathHelper.floor(double_19 - double_16), startZ);
				int int_14 = Math.max(MathHelper.floor(double_17 + double_16), int_11);
				int int_15 = Math.max(MathHelper.floor(double_18 + double_16), int_12);
				int int_16 = Math.max(MathHelper.floor(double_19 + double_16), int_13);
				
				for (int x = int_11; x <= int_14; ++x) {
					double double_20 = ((double) x + 0.5D - double_17) / double_16;
					if (double_20 * double_20 < 1.0D) {
						for (int y = int_12; y <= int_15; ++y) {
							double double_21 = ((double) y + 0.5D - double_18) / double_16;
							if (double_20 * double_20 + double_21 * double_21 < 1.0D) {
								for (int z = int_13; z <= int_16; ++z) {
									double double_22 = ((double) z + 0.5D - double_19) / double_16;
									if (double_20 * double_20 + double_21 * double_21 + double_22 * double_22 < 1.0D) {
										int int_20 = x - startX + (y - yPosition) * xSize + (z - startZ) * xSize * int_5;
										if (!bitSet.get(int_20)) {
											bitSet.set(int_20);
											blockPos.set(x, y, z);
											if (world.getBlockState(blockPos).getBlock() == HallowedBlocks.TAINTED_STONE) {
												world.setBlockState(blockPos, oreFeatureConfig.state, 2);
												++stonesPlaced;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return stonesPlaced > 0;
	}
}
