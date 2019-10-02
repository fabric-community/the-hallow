package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.item.SkirtCostume;

import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems
{
	public static final Item BLAZE_SKIRT = new SkirtCostume(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public static void init() {
		TrinketSlots.addSubSlot("legs", "belt", new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));

		register("blaze_skirt", BLAZE_SKIRT);
    }

    private static Item register(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyItems() {
        // NO-OP
    }
}
