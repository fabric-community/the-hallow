package com.fabriccommunity.spookytime;

import io.github.indicode.fabric.tinyconfig.ModConfig;

/**
 * @author Indigo Amann
 */
public class SpookyConfig {
    private static ModConfig modConfig = new ModConfig(SpookyTime.MODID);
    public static void sync(boolean overwrite) {
        modConfig.configure(overwrite, config -> {

        });
    }
}
