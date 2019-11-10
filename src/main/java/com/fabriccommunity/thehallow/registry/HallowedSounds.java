package com.fabriccommunity.thehallow.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;

public class HallowedSounds {
	public static final SoundEvent DOOT = register("doot");
	public static final SoundEvent MEGALADOOT = register("megaladoot");
	public static final SoundEvent SHOTGUN_SHOT = register("shotgun_shot");
	public static final SoundEvent SHOTGUN_NO_AMMO = register("shotgun_no_ammo");
	public static final SoundEvent SHOTGUN_SHELL_RELOAD = register("shotgun_shell_reload");
	public static final SoundEvent CROW_AMBIENT = register("entity.crow.ambient");
	
	private HallowedSounds() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	private static SoundEvent register(String name) {
		return Registry.register(Registry.SOUND_EVENT, TheHallow.id(name), new SoundEvent(TheHallow.id(name)));
	}
}
