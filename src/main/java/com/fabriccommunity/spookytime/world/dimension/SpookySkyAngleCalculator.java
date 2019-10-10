package com.fabriccommunity.spookytime.world.dimension;

import com.github.draylar.worldtraveler.api.dimension.utils.SkyAngleCalculator;

public class SpookySkyAngleCalculator implements SkyAngleCalculator {
	// Day fraction function created using https://www.desmos.com/calculator/h5zvtx8jj6
	// and also https://minecraft.gamepedia.com/Day
	static final int cycleStart = 12567;
	static final float cycleStartDayFraction = cycleStart / 24000f;
	static final int cycleEnd = 22900;
	static final float cycleEndDayFraction = cycleEnd / 24000f;
	static final float cycleLength = cycleEnd - cycleStart;

	protected float repeatThisVee(long ticks) {
		return Math.abs(ticks / 24000f - cycleStartDayFraction) + cycleStartDayFraction;
	}

	protected float zigzagDayFunction(long ticks) {
		return repeatThisVee((long) ((ticks - cycleLength - cycleStart) % (2 * cycleLength)));
	}

	protected float waveDayFunction(long ticks) {
		return (
			(float) Math.sin(
				((double) (
					(2 * ticks - cycleStart - cycleEnd) / (2 * cycleLength)
				)) * Math.PI
			)
		) / ((2f * 24000f) / cycleLength) + (cycleStart + cycleEnd) / (2f * 24000f);
	}

	@Override //combination of wave and zigzag day functions
	public float calculate(long ticks, float v) {
		return (2f * zigzagDayFunction(ticks) + waveDayFunction(ticks)) / 3f;
	}
}
