package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.world.SpookyBiomeSource;
import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder;
import com.github.draylar.worldtraveler.api.dimension.EntityPlacerBuilder;
import com.github.draylar.worldtraveler.api.dimension.FabricDimension;
import com.github.draylar.worldtraveler.api.world.ChunkGeneratorTemplateBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

public class SpookyDimensions
{
    public static final FabricDimensionType SPOOKY = FabricDimensionType.builder()
            .skyLight(true)
            .factory((world, type) -> new DimensionBuilder()
                    .renderFog(true)
                    .fogColor((long_1, float_1) -> new Vec3d(75F / 255F, 0F / 255F, 125F / 255F))
                    .visibleSky(false)
                    .setChunkGenerator(ChunkGeneratorType.SURFACE.create(world, new SpookyBiomeSource(world.getSeed()), new OverworldChunkGeneratorConfig()))
                    .setLightLevelsToBrightness(getLightLevels())
                    .build(world, type))
            .defaultPlacer(new EntityPlacerBuilder().build())
            .buildAndRegister(new Identifier(SpookyTime.MODID, "spooky"));

    public static void init() {

    }

    public static float[] getLightLevels() {
        float[] lightLevels = new float[16];

        for(int i = 0; i <= 15; ++i) {
            float lightLevel = 1.0F - (float) i / 15.0F;
            lightLevels[i] = ((1.0F - lightLevel) / (lightLevel * 3.0F + 1.0F) * 1.0F + 0.0F) / 2;
        }

        return lightLevels;
    }

    private SpookyDimensions() {
        // NO-OP
    }
}
