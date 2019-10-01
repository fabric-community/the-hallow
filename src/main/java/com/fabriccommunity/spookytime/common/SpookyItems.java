package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{


    public static void init() {
        // NO-OP
    }

    private static Item register(String name, Item item)
    {
        return Registry.register(Registry.ITEM, SpookyTime.id(name), item);
    }

    private SpookyItems() {
        // NO-OP
    }
}
