package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.HallowBiomeSource;
import com.fabriccommunity.thehallow.world.HallowChunkGeneratorConfig;
import com.fabriccommunity.thehallow.world.HallowChunkGeneratorType;
import com.fabriccommunity.thehallow.world.dimension.HallowFogColorCalculator;
import com.fabriccommunity.thehallow.world.dimension.HallowSkyAngleCalculator;
import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder;
import com.github.draylar.worldtraveler.api.dimension.EntityPlacerBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;

public class HallowDimensions {
	public static final FabricDimensionType HALLOW = FabricDimensionType.builder()
		.skyLight(true)
		.factory((world, type) -> new DimensionBuilder()
			.renderFog(true)
			.fogColor(new HallowFogColorCalculator())
			.visibleSky(true)
			.skyAngle(new HallowSkyAngleCalculator())
			.setChunkGenerator(HallowChunkGeneratorType.INSTANCE.create(world, new HallowBiomeSource(world.getSeed()), new HallowChunkGeneratorConfig()))
			.setLightLevelsToBrightness(getLightLevels())
			.build(world, type))
		.defaultPlacer(new EntityPlacerBuilder().build())
		.buildAndRegister(TheHallow.id("spooky"));
	
	private HallowDimensions() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static float[] getLightLevels() {
		float[] lightLevels = new float[16];
		
		for (int i = 0; i <= 15; ++i) {
			float lightLevel = 1.0F - (float) i / 15.0F;
			lightLevels[i] = ((1.0F - lightLevel) / (lightLevel * 3.0F + 1.0F) * 1.0F + 0.0F);
		}
		
		return lightLevels;
	}
}
