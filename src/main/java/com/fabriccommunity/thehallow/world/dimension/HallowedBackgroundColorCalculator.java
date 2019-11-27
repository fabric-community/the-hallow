package com.fabriccommunity.thehallow.world.dimension;

import com.github.draylar.worldtraveler.api.dimension.utils.BackgroundColorCalculator;

public class HallowedBackgroundColorCalculator implements BackgroundColorCalculator {

	private static final float[] COLOR = {0, 0, 0, 0};
	
	@Override
	public float[] calculate(float x, float y) {
		return COLOR;
	}

}
