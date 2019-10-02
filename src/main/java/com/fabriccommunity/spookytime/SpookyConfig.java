package com.fabriccommunity.spookytime;

import io.github.indicode.fabric.tinyconfig.ModConfig;

/**
 * @author Indigo Amann
 */
public class SpookyConfig {
    private static ModConfig modConfig = new ModConfig(SpookyTime.MODID);
    public static class SpookyWeather {
        public static int thunderModifier = 80;
        public static boolean lessClearSkies = true;
    }
    public static void sync(boolean overwrite) {
        modConfig.configure(overwrite, config -> {
            config.accessChild("weather", weather -> {
                SpookyWeather.thunderModifier = weather.getInt("thunder_modifier", SpookyWeather.thunderModifier, "Amount the thunder time is devided by");
                SpookyWeather.lessClearSkies = weather.getBool("less_clear_skies", SpookyWeather.lessClearSkies, "Make it so there are less clear skies, more rain and thunder");
            });
        });
    }
}
