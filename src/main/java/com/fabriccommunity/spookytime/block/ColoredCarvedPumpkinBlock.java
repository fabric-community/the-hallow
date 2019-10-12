package com.fabriccommunity.spookytime.block;

import net.minecraft.block.CarvedPumpkinBlock;

public class ColoredCarvedPumpkinBlock extends CarvedPumpkinBlock {
	private final ColoredPumpkinBlock.PumpkinColor color;

	public ColoredCarvedPumpkinBlock(Settings block$Settings_1, ColoredPumpkinBlock.PumpkinColor color) {
		super(block$Settings_1);
		this.color = color;
	}

	public ColoredPumpkinBlock.PumpkinColor getColor() {
		return this.color;
	}
}
