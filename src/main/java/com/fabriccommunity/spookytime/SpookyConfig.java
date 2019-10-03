package com.fabriccommunity.spookytime;

import io.github.indicode.fabric.tinyconfig.ModConfig;

/**
 * @author Indigo Amann
 */
public class SpookyConfig {
	private static ModConfig modConfig = new ModConfig(SpookyTime.MOD_ID);
	
	public static void sync(boolean overwrite) {
		modConfig.configure(overwrite, config -> {
			config.accessChild("weather", weather -> {
				SpookyWeather.thunderModifier = weather.getInt("thunder_modifier", SpookyWeather.thunderModifier, "Amount the thunder time is divided by. Set to 1 to disable");
				SpookyWeather.lessClearSkies = weather.getBool("less_clear_skies", SpookyWeather.lessClearSkies, "Make it so there are less clear skies, more rain and thunder");
			});
			config.accessChild("pumpkins_on_mobs", pumpkinMobs -> {
				PumpkinMobs.headArmor = pumpkinMobs.getBool("pumpkin_head", PumpkinMobs.headArmor, "Zombies, Skeletons, and Wither Skeletons have a 1/3 chance of spawning with a pumpkin on their head");
				PumpkinMobs.endermen = pumpkinMobs.getBool("endermen_hold", PumpkinMobs.endermen, "Endermen have a 1/3 chance of spawning holding a pumpkin");
			});
			config.accessChild("trick_or_treating", trickOrTreating -> {
				TrickOrTreating.enableTricks = trickOrTreating.getBool("enable_tricks", TrickOrTreating.enableTricks, "Enables a chance for trick or treating to result in the villagers becoming witches");
				TrickOrTreating.trickChance = trickOrTreating.getInt("trick_chance", TrickOrTreating.trickChance, "Tricks, if enabled, will have a one in (this number) chance of happening");
			});
		});
	}
	
	public static class SpookyWeather {
		public static int thunderModifier = 80;
		public static boolean lessClearSkies = true;
	}
	
	public static class PumpkinMobs {
		public static boolean headArmor = true;
		public static boolean endermen = true;
	}
	
	public static class TrickOrTreating {
		public static boolean enableTricks = true;
		public static int trickChance = 100;
	}
}
