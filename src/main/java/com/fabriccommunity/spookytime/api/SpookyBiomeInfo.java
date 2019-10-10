package com.fabriccommunity.spookytime.api;

import net.minecraft.util.math.Vec3d;

public interface SpookyBiomeInfo {
	Vec3d getFogColor();
	
	float getFogIntensity();
	
	boolean shouldFogRender();
}
