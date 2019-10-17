package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.HallowedBiomeSource;
import com.fabriccommunity.thehallow.world.HallowedChunkGeneratorConfig;
import com.fabriccommunity.thehallow.world.HallowedChunkGeneratorType;
import com.fabriccommunity.thehallow.world.dimension.HallowedFogColorCalculator;
import com.fabriccommunity.thehallow.world.dimension.HallowedSkyAngleCalculator;
import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder;
import com.github.draylar.worldtraveler.api.dimension.EntityPlacerBuilder;

public class HallowedDimensions {
	public static final FabricDimensionType SPOOKY = FabricDimensionType.builder()
		.skyLight(true)
		.factory((world, type) -> new DimensionBuilder()
			.renderFog(true)
			.fogColor(new HallowedFogColorCalculator())
			.visibleSky(true)
			.skyAngle(new HallowedSkyAngleCalculator())
			.setChunkGenerator(HallowedChunkGeneratorType.INSTANCE.create(world, new HallowedBiomeSource(world.getSeed()), new HallowedChunkGeneratorConfig()))
			.setLightLevelsToBrightness(getLightLevels())
			.build(world, type))
		.defaultPlacer(new EntityPlacerBuilder().build())
		.buildAndRegister(TheHallow.id("spooky"));
	
	private HallowedDimensions() {
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
