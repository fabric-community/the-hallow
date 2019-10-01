package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.biome.SpookyForestBiome;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class SpookyBiomes
{
    public static SpookyForestBiome SPOOKY_FOREST;

    public static void init() {
        SPOOKY_FOREST = register("spooky_forest", new SpookyForestBiome());
    }

    private static <T extends Biome> T register(String name, T biome)
    {
        return Registry.register(Registry.BIOME, new Identifier(SpookyTime.MODID, name), biome);
    }

    private SpookyBiomes() {
        // NO-OP
    }
}
