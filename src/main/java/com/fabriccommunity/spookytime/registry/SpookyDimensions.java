package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.SpookyBiomeSource;
import com.fabriccommunity.spookytime.world.SpookyChunkGeneratorConfig;
import com.fabriccommunity.spookytime.world.SpookyChunkGeneratorType;
import com.fabriccommunity.spookytime.world.dimension.SpookyFogColorCalculator;
import com.fabriccommunity.spookytime.world.dimension.SpookySkyAngleCalculator;
import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder;
import com.github.draylar.worldtraveler.api.dimension.EntityPlacerBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.util.math.Vec3d;

public class SpookyDimensions {
	public static final FabricDimensionType SPOOKY = FabricDimensionType.builder()
		.skyLight(true)
		.factory((world, type) -> new DimensionBuilder()
			.renderFog(true)
			.fogColor(new SpookyFogColorCalculator())
			.visibleSky(true)
			.skyAngle(new SpookySkyAngleCalculator())
			.setChunkGenerator(SpookyChunkGeneratorType.INSTANCE.create(world, new SpookyBiomeSource(world.getSeed()), new SpookyChunkGeneratorConfig() {
				SpookyChunkGeneratorConfig init() {
					defaultBlock = SpookyBlocks.TAINTED_STONE.getDefaultState();
					defaultFluid = SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
					return this;
				}
			}.init()))
			.setLightLevelsToBrightness(getLightLevels())
			.build(world, type))
		.defaultPlacer(new EntityPlacerBuilder().build())
		.buildAndRegister(SpookyTime.id("spooky"));

	private SpookyDimensions() {
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
