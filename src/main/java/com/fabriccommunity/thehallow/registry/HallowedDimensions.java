package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;

import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.HallowedBiomeSource;
import com.fabriccommunity.thehallow.world.HallowedChunkGeneratorConfig;
import com.fabriccommunity.thehallow.world.HallowedChunkGeneratorType;
import com.fabriccommunity.thehallow.world.dimension.HallowedFogColorCalculator;
import com.fabriccommunity.thehallow.world.dimension.HallowedSkyAngleCalculator;
import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder;

public class HallowedDimensions {
	public static EntityPlacer FIND_SURFACE = (entity, world, dim, offsetX, offsetZ) -> new BlockPattern.TeleportTarget(new Vec3d(entity.getBlockPos().getX(), world.getChunk(entity.getBlockPos().getX() >> 4, entity.getBlockPos().getZ() >> 4).sampleHeightmap(Heightmap.Type.MOTION_BLOCKING, entity.getBlockPos().getX() & 15, entity.getBlockPos().getZ() & 15) + 1, entity.getBlockPos().getZ()), entity.getVelocity(), (int)entity.yaw);
	
	public static final FabricDimensionType THE_HALLOW = FabricDimensionType.builder()
		.skyLight(true)
		.factory((world, type) -> new DimensionBuilder()
			.hasThickFog(true)
			.fogColor(new HallowedFogColorCalculator())
			.visibleSky(true)
			.skyAngle(new HallowedSkyAngleCalculator())
			.setChunkGenerator(HallowedChunkGeneratorType.INSTANCE.create(world, new HallowedBiomeSource(world.getSeed()), new HallowedChunkGeneratorConfig()))
			.setLightLevelsToBrightness(getLightLevels())
			.build(world, type))
		.defaultPlacer(FIND_SURFACE)
		.buildAndRegister(TheHallow.id("the_hallow"));
	
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
