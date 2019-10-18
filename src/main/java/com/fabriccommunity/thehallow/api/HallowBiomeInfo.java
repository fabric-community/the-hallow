package com.fabriccommunity.thehallow.api;

import net.minecraft.util.math.Vec3d;

public interface HallowBiomeInfo {
	Vec3d getFogColor();
	
	float getFogIntensity();
	
	boolean shouldFogRender();
}
