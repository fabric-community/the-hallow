package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{
	public static Item PUMPCOWN_SPAWN_EGG;

    public static BlockItem TINY_PUMPKIN;

    public static void init() {
        PUMPCOWN_SPAWN_EGG = register("pumpcown_spawn_egg", new SpawnEggItem(SpookyEntities.PUMPCOWN, 8273166, 14912029, new Item.Settings().group(ItemGroup.MISC)));
      
        TINY_PUMPKIN = register("tiny_pumpkin", new BlockItem(SpookyBlocks.TINY_PUMPKIN, new Item.Settings().group(ItemGroup.MISC)));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyItems() {
        // NO-OP
    }
}
