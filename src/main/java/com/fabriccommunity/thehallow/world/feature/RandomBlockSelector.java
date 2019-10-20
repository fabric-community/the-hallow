package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

@FunctionalInterface
public interface RandomBlockSelector {
	BlockState getSelection(Random random, BlockPos targetPos);
}
