package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SpookySounds {
	public static final SoundEvent DOOT = register("doot");
	public static final SoundEvent CROW_AMBIENT = register("entity.crow.ambient");

	public static void init() {
		// NO-OP
	}

	private static SoundEvent register(String name) {
		return Registry.register(Registry.SOUND_EVENT, SpookyTime.id(name), new SoundEvent(SpookyTime.id(name)));
	}
}
