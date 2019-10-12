package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class ColoredPumpkinFeature {
	private ColoredPumpkinFeature(){}

	private static final ImmutableList<Block> COMMON_PUMPKIN_TYPES = new ImmutableList.Builder<Block>()
		.add(SpookyBlocks.BLUE_PUMPKIN)
		.add(SpookyBlocks.RED_PUMPKIN)
		.add(SpookyBlocks.TAN_PUMPKIN)
		.add(SpookyBlocks.WHITE_PUMPKIN)
		.add(SpookyBlocks.YELLOW_PUMPKIN)
		.build();

	public static final RandomChoiceSelector COLORED_PUMPKINS = (random, targetPos) -> {
		int sel = random.nextInt(100);
		if(sel < 60) {
			return Blocks.PUMPKIN.getDefaultState();
		} else if (sel >=60 && sel <99) {
			int selection = random.nextInt(5);
			BlockState state = COMMON_PUMPKIN_TYPES.get(selection).getDefaultState();
			if(state == null) {
				return Blocks.PUMPKIN.getDefaultState();
			}
			return state;
		} else {
			if(random.nextBoolean()) {
				return SpookyBlocks.WITCHED_PUMPKIN.getDefaultState();
			} else {
				return SpookyBlocks.RAINBOW_PUMPKIN.getDefaultState();
			}
		}
	};
}
