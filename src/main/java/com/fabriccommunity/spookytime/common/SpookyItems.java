package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.items.SpookyMaskItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{
    public static final Item SPOOKY_MASK = register("spooky_mask", new SpookyMaskItem(new Item.Settings()));

    public static void init() {
        // NO-OP
    }

    private static Item register(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyItems() {
        // NO-OP
    }
}
