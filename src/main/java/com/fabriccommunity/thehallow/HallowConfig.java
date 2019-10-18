package com.fabriccommunity.thehallow;

import io.github.indicode.fabric.tinyconfig.ModConfig;

/**
 * @author Indigo Amann
 */
public class HallowConfig {
	private static ModConfig modConfig = new ModConfig(TheHallow.MOD_ID);
	
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
			config.accessChild("tiny_pumpkin", tinyPumpkin -> {
				TinyPumpkin.waterloggable = tinyPumpkin.getBool("waterloggable", TinyPumpkin.waterloggable, "Lets the Tiny Pumpkin be waterlogged");
			});
			config.accessChild("trick_or_treating", trickOrTreating -> {
				TrickOrTreating.enableTricks = trickOrTreating.getBool("enable_tricks", TrickOrTreating.enableTricks, "Enables a chance for trick or treating to result in the villagers becoming witches");
				TrickOrTreating.trickChance = trickOrTreating.getInt("trick_chance", TrickOrTreating.trickChance, "Tricks, if enabled, will have a one in (this number) chance of happening");
			});
			config.accessChild("trumpet_skeleton", trumpetSkeleton -> {
				TrumpetSkeleton.enabled = trumpetSkeleton.getBool("enabled", TrumpetSkeleton.enabled, "If enabled, Skeletons can sometimes spawn with Trumpets");
				TrumpetSkeleton.chance = trumpetSkeleton.getInt("chance", TrumpetSkeleton.chance, "Skeletons will have a one in (this number) chance of spawning with a trumpet");
			});
			config.accessChild("fog", fog -> {
				SpookyFog.fogSmoothingRadius = fog.getInt("fogSmoothingRadius", SpookyFog.fogSmoothingRadius, "Determines the radius in which biomes are checked to smooth out biome fog colors. Lower values = less intensive.");
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
	
	public static class TinyPumpkin {
		public static boolean waterloggable = true;
	}
	
	public static class TrickOrTreating {
		public static boolean enableTricks = true;
		public static int trickChance = 100;
	}
	
	public static class TrumpetSkeleton {
		public static boolean enabled = true;
		public static int chance = 50;
	}
	
	public static class SpookyFog {
		public static int fogSmoothingRadius = 8;
	}
}
