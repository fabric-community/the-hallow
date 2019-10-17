package com.fabriccommunity.thehallow.api;

import net.minecraft.util.math.Vec3d;

public interface HallowedBiomeInfo {
	Vec3d getFogColor();
	
	float getFogIntensity();
	
	boolean shouldFogRender();
}
