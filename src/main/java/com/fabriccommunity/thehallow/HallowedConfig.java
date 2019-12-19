package com.fabriccommunity.thehallow;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonGrammar;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HallowedConfig {
	
	private static final Jankson JANKSON = Jankson.builder().build();
	
	public static void sync() {
		File configFile = new File("config/thehallow.json5");
		JsonObject config = new JsonObject();
		if(configFile.exists()) {
			try {
				config = JANKSON.load(configFile);
				loadFrom(config);
				writeConfigFile(configFile, config);
			} catch (IOException | SyntaxError e) {
				TheHallow.LOGGER.error("The Hallow config could not be loaded. Default values will be used.", e);
			}
		} else {
			saveTo(config);
			writeConfigFile(configFile, config);
		}
	}
	
	private static void writeConfigFile(File file, JsonObject config) {
		saveTo(config);
		try(OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
			out.write(config.toJson(JsonGrammar.JANKSON).getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			TheHallow.LOGGER.error("The Hallow config could not be written. This probably won't cause any problems, but it shouldn't happen.", e);
		}
	}

	public static class HallowedDimension {
		public static boolean waterVaporizes = true;
	}
	
	public static class HallowedWeather {
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
	
	public static class HallowedFog {
		public static int fogSmoothingRadius = 8;
	}
	
	public static class Tweaks {
		public static boolean pumpkinPieBlock = true;
	}
	
	//deserializer
	public static void loadFrom(JsonObject obj) {
		JsonObject dimension = getObjectOrEmpty("dimension", obj);
		HallowedDimension.waterVaporizes = dimension.getBoolean("water_vaporizes", HallowedDimension.waterVaporizes);

		JsonObject weather = getObjectOrEmpty("weather", obj);
		HallowedWeather.thunderModifier = weather.getInt("thunder_modifier", HallowedWeather.thunderModifier);
		HallowedWeather.lessClearSkies = weather.getBoolean("less_clear_skies", HallowedWeather.lessClearSkies);
		
		JsonObject pumpkins = getObjectOrEmpty("pumpkins_on_mobs", obj);
		PumpkinMobs.headArmor = pumpkins.getBoolean("pumpkin_head", PumpkinMobs.headArmor);
		PumpkinMobs.endermen = pumpkins.getBoolean("endermen_hold", PumpkinMobs.endermen);
		
		JsonObject tinyPumpkin = getObjectOrEmpty("tiny_pumpkin", obj);
		TinyPumpkin.waterloggable = tinyPumpkin.getBoolean("waterloggable", TinyPumpkin.waterloggable);
		
		JsonObject trickOrTreating = getObjectOrEmpty("trick_or_treating", obj);
		TrickOrTreating.enableTricks = trickOrTreating.getBoolean("enable_tricks", TrickOrTreating.enableTricks);
		TrickOrTreating.trickChance = trickOrTreating.getInt("trick_chance", TrickOrTreating.trickChance);
		
		JsonObject trumpetSkeleton = getObjectOrEmpty("trumpet_skeleton", obj);
		TrumpetSkeleton.enabled = trumpetSkeleton.getBoolean("enabled", TrumpetSkeleton.enabled);
		TrumpetSkeleton.chance = trumpetSkeleton.getInt("chance", TrumpetSkeleton.chance);
		
		JsonObject fog = getObjectOrEmpty("fog", obj);
		HallowedFog.fogSmoothingRadius = fog.getInt("fogSmoothingRadius", HallowedFog.fogSmoothingRadius);
		
		JsonObject tweaks = getObjectOrEmpty("tweaks", obj);
		Tweaks.pumpkinPieBlock = tweaks.getBoolean("pumpkin_pie_block", Tweaks.pumpkinPieBlock);
	}
		
	//serializer
	public static void saveTo(JsonObject obj) {
		JsonObject weather = defaultPutButNotNull("weather", new JsonObject(), obj);
		weather.putDefault("thunder_modifier", HallowedWeather.thunderModifier, "Amount the thunder time is divided by. Set to 1 to disable");
		weather.putDefault("less_clear_skies", HallowedWeather.lessClearSkies, "Make it so there are less clear skies, more rain and thunder");
		
		JsonObject pumpkins = defaultPutButNotNull("pumpkins_on_mobs", new JsonObject(), obj);
		pumpkins.putDefault("pumpkin_head", PumpkinMobs.headArmor, "Zombies, Skeletons, and Wither Skeletons have a 1/3 chance of spawning with a pumpkin on their head");
		pumpkins.putDefault("endermen_hold", PumpkinMobs.endermen, "Endermen have a 1/3 chance of spawning holding a pumpkin");
		
		JsonObject tinyPumpkin = defaultPutButNotNull("tiny_pumpkin", new JsonObject(), obj);
		tinyPumpkin.putDefault("waterloggable", TinyPumpkin.waterloggable, "Lets the Tiny Pumpkin be waterlogged");
		
		JsonObject trickOrTreating = defaultPutButNotNull("trick_or_treating", new JsonObject(), obj);
		trickOrTreating.putDefault("enable_tricks", TrickOrTreating.enableTricks, "Enables a chance for trick or treating to result in the villagers becoming witches");
		trickOrTreating.putDefault("trick_chance", TrickOrTreating.trickChance, "Tricks, if enabled, will have a one in (this number) chance of happening");
		
		JsonObject trumpetSkeleton = defaultPutButNotNull("trumpet_skeleton", new JsonObject(), obj);
		trumpetSkeleton.putDefault("enabled", TrumpetSkeleton.enabled, "If enabled, Skeletons can sometimes spawn with Trumpets");
		trumpetSkeleton.putDefault("chance", TrumpetSkeleton.chance, "Skeletons will have a one in (this number) chance of spawning with a trumpet");
		
		JsonObject fog = defaultPutButNotNull("fog", new JsonObject(), obj);
		fog.putDefault("fogSmoothingRadius", HallowedFog.fogSmoothingRadius, "Determines the radius in which biomes are checked to smooth out biome fog colors. Lower values = less intensive.");
		
		JsonObject tweaks = defaultPutButNotNull("tweaks", new JsonObject(), obj);
		tweaks.putDefault("pumpkin_pie_block", Tweaks.pumpkinPieBlock, "If true, allows placing pumpkin pie blocks using the vanilla pumpkin pie item");
	}
		
	private static JsonObject getObjectOrEmpty(String key, JsonObject on) {
		JsonObject obj = on.getObject(key);
		return obj != null ? obj : new JsonObject();
	}
	
	
	@SuppressWarnings("unchecked")
	private static <T extends JsonElement> T defaultPutButNotNull(String key, T value, JsonObject obj) {
		JsonElement result = obj.putDefault(key, value, value.getClass(), null);
		return result != null ? (T) result : value;
	}
}
