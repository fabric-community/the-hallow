package com.fabriccommunity.thehallow.world.feature;

import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class ColoredPumpkinFeature {
	private ColoredPumpkinFeature(){
		// NO-OP
	}

	private static final ImmutableList<Block> COMMON_PUMPKIN_TYPES = new ImmutableList.Builder<Block>()
		.add(HallowBlocks.BLUE_PUMPKIN)
		.add(HallowBlocks.RED_PUMPKIN)
		.add(HallowBlocks.TAN_PUMPKIN)
		.add(HallowBlocks.WHITE_PUMPKIN)
		.add(HallowBlocks.YELLOW_PUMPKIN)
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
				return HallowBlocks.WITCHED_PUMPKIN.getDefaultState();
			} else {
				return HallowBlocks.RAINBOW_PUMPKIN.getDefaultState();
			}
		}
	};
}
