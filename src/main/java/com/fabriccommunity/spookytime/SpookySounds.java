package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookySounds {
    public static final SoundEvent DOOT = register("doot");

    public static void init() {
        // NO-OP
    }

    private static SoundEvent register(String name)
    {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(SpookyTime.MODID, name), new SoundEvent(new Identifier(SpookyTime.MODID, name)));
    }
}
