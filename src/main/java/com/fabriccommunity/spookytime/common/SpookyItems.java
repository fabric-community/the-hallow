package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;

import com.fabriccommunity.spookytime.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{
    public static Item PUMPCOWN_SPAWN_EGG;

    public static Item TINY_PUMPKIN;
    public static Item DECEASED_DIRT;
    public static Item DECEASED_GRASS_BLOCK;
    public static Item TAINTED_SAND;
    public static Item TAINTED_GRAVEL;
    public static Item SPOOKY_TRUMPET;

    public static void init() {
        PUMPCOWN_SPAWN_EGG = register("pumpcown_spawn_egg", new SpawnEggItem(SpookyEntities.PUMPCOWN, 8273166, 14912029, new Item.Settings().group(ItemGroup.MISC)));
      
        TINY_PUMPKIN = register("tiny_pumpkin", new BlockItem(SpookyBlocks.TINY_PUMPKIN, new Item.Settings().group(ItemGroup.MISC)));
      
        DECEASED_DIRT = register("deceased_dirt", new BlockItem(SpookyBlocks.DECEASED_DIRT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        DECEASED_GRASS_BLOCK = register("deceased_grass_block", new BlockItem(SpookyBlocks.DECEASED_GRASS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        TAINTED_SAND = register("tainted_sand", new BlockItem(SpookyBlocks.TAINTED_SAND, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        TAINTED_GRAVEL = register("tainted_gravel", new BlockItem(SpookyBlocks.TAINTED_GRAVEL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        SPOOKY_TRUMPET =  register("spooky_trumpet", new SpookyTrumpetItem(new Item.Settings().group(ItemGroup.MISC)));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(SpookyTime.MODID, name), item);
    }

	private SpookyItems() {
		// NO-OP
	}
}
