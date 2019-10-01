package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
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

public class SpookyDimensions
{
    public static FabricDimensionType SPOOKY;

    public static void init() {
        float[] lightLevels = new float[16];

        for(int int_1 = 0; int_1 <= 15; ++int_1) {
            float float_2 = 1.0F - (float)int_1 / 15.0F;
            lightLevels[int_1] = ((1.0F - float_2) / (float_2 * 3.0F + 1.0F) * 1.0F + 0.0F) / 2;
        }

        SPOOKY = FabricDimensionType.builder()
                .skyLight(true)
                .factory((world, type) -> new DimensionBuilder()
                        .renderFog(true)
                        .fogColor((long_1, float_1) -> new Vec3d(75F / 255F, 0F / 255F, 125F / 255F))
                        .visibleSky(false)
                        .setChunkGenerator(new ChunkGeneratorTemplateBuilder().getSingleBiomeVanillaBuilder(world, SpookyBiomes.SPOOKY_FOREST))
                        .setLightLevelsToBrightness(lightLevels)
                        .build(world, type))
                .defaultPlacer(new EntityPlacerBuilder().build())
                .buildAndRegister(new Identifier(SpookyTime.MODID, "spooky"));
    }

    private SpookyDimensions() {
        // NO-OP
    }
}
