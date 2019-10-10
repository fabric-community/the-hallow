package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Indigo Amann
 */
public class SmallSkeletalTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private static final BlockState LOG = Blocks.BONE_BLOCK.getDefaultState();

	public SmallSkeletalTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean boolean1) {
		super(function, boolean1);
	}

	protected static boolean isPlantableOn(TestableWorld testableWorld, BlockPos blockPos) {
		return testableWorld.testBlockState(blockPos, (blockState_1) -> {
			Block block = blockState_1.getBlock();
			return Block.isNaturalDirt(block) || block == Blocks.GRASS_BLOCK || block == SpookyBlocks.DECEASED_GRASS_BLOCK;
		});
	}

	public boolean generate(Set<BlockPos> set, ModifiableTestableWorld world, Random random, BlockPos pos, MutableIntBoundingBox mibb) {
		if (!isPlantableOn(world, pos.down())) {
			return false;
		}
		int y;
		for (y = 0; y < random.nextInt(5) + 3; y++) {
			addLog(set, world, pos.offset(Direction.UP, y), Direction.Axis.Y, mibb);
		}
		if (random.nextBoolean()) {
			Direction direction = Direction.Type.HORIZONTAL.random(random);
			BlockPos top = pos.offset(Direction.UP, y);
			for (int i = 1; i < random.nextInt(3); i++) {
				addLog(set, world, top.offset(direction, i), direction.getAxis(), mibb);
			}
		}
		return true;
	}

	private void addLog(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos, Direction.Axis axis, MutableIntBoundingBox mutableIntBoundingBox) {
		if (canTreeReplace(modifiableTestableWorld, blockPos)) {
			this.setBlockState(set, modifiableTestableWorld, blockPos, LOG.with(PillarBlock.AXIS, axis), mutableIntBoundingBox);
		}
	}
}
