package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{


    public static BlockItem TINY_PUMPKIN;

    public static void init() {
        TINY_PUMPKIN = register("tiny_pumpkin", new BlockItem(SpookyBlocks.TINY_PUMPKIN, new Item.Settings().group(ItemGroup.MISC)));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyItems() {
        // NO-OP
    }
}
