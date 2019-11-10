package com.fabriccommunity.thehallow.world.feature;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class ColoredPumpkinFeature {
	private ColoredPumpkinFeature(){
		// NO-OP
	}

	private static final ImmutableList<Block> COMMON_PUMPKIN_TYPES = new ImmutableList.Builder<Block>()
		.add(HallowedBlocks.BLUE_PUMPKIN)
		.add(HallowedBlocks.RED_PUMPKIN)
		.add(HallowedBlocks.TAN_PUMPKIN)
		.add(HallowedBlocks.WHITE_PUMPKIN)
		.add(HallowedBlocks.YELLOW_PUMPKIN)
		.build();

	public static final RandomBlockSelector COLORED_PUMPKINS = (random, targetPos) -> {
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
				return HallowedBlocks.TINY_WITCHED_PUMPKIN.getDefaultState();
			} else {
				return HallowedBlocks.RAINBOW_PUMPKIN.getDefaultState();
			}
		}
	};
}
