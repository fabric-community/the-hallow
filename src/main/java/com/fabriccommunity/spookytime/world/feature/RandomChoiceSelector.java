package com.fabriccommunity.spookytime.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

@FunctionalInterface
public interface RandomChoiceSelector {
	BlockState getSelection(Random random, BlockPos targetPos);
}
