package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import com.mojang.datafixers.Dynamic;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Indigo Amann
 */
public class SmallSkeletalTreeFeature extends AbstractTreeFeature<TreeFeatureConfig> {
	private static final BlockState LOG = Blocks.BONE_BLOCK.getDefaultState();
	
	public SmallSkeletalTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function) {
		super(function);
	}
	
	protected static boolean isPlantableOn(TestableWorld testableWorld, BlockPos blockPos) {
		return testableWorld.testBlockState(blockPos, (blockState_1) -> {
			Block block = blockState_1.getBlock();
			return block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.GRASS_BLOCK || block == HallowedBlocks.DECEASED_GRASS_BLOCK;
		});
	}
	
	@Override
	public boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
		if (!isPlantableOn(world, pos.down())) {
			return false;
		}
		int y;
		for (y = 0; y < random.nextInt(5) + 3; y++) {
			addLog(logPositions, world, pos.offset(Direction.UP, y), Direction.Axis.Y, blockBox);
		}
		if (random.nextBoolean()) {
			Direction direction = Direction.Type.HORIZONTAL.random(random);
			BlockPos top = pos.offset(Direction.UP, y);
			for (int i = 1; i < random.nextInt(3); i++) {
				addLog(logPositions, world, top.offset(direction, i), direction.getAxis(), blockBox);
			}
		}
		return true;
	}
	
	private void addLog(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos, Direction.Axis axis, BlockBox mutableIntBoundingBox) {
		if (canTreeReplace(modifiableTestableWorld, blockPos)) {
			set.add(blockPos);
			this.setBlockState(modifiableTestableWorld, blockPos, LOG.with(PillarBlock.AXIS, axis), mutableIntBoundingBox);
		}
	}
}
