package com.fabriccommunity.spookytime.world.dimension;

import com.github.draylar.worldtraveler.api.dimension.utils.SkyAngleCalculator;

import net.minecraft.util.math.MathHelper;

public class SpookySkyAngleCalculator implements SkyAngleCalculator {
	@Override
	public float calculate(long l, float v) {
		double double_1 = MathHelper.fractionalPart((double) l / 24000.0D - 0.25D);
		double double_2 = 0.5D - Math.cos(double_1 * 3.141592653589793D) / 2.0D;
		return (float) (double_1 * 2.0D + double_2) / 3.0F;
	}
}
