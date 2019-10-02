package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.item.CandyItem;
import com.fabriccommunity.spookytime.item.SkirtCostume;
import dev.emi.trinkets.api.TrinketSlots;

import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyItems {
	  public static Item BLAZE_SKIRT;
	  public static Item CARAMEL_APPLE;
	  public static Item PUMPKIN_CANDY;
	  public static Item RARE_CANDY;
	  public static Item WINGED_STRAWBERRY_CANDY;
	  public static Item PUMPCOWN_SPAWN_EGG;
    public static Item BAKED_PUMPKIN_SEEDS;

    public static BlockItem TINY_PUMPKIN;
	
	  public static void init() {
		    TrinketSlots.addSubSlot("legs", "belt", new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));
		    BLAZE_SKIRT = register("blaze_skirt", new SkirtCostume(new Item.Settings().group(ItemGroup.MISC).maxCount(1)));
		    CARAMEL_APPLE = register("caramel_apple", new CandyItem(new Item.Settings().group(ItemGroup.FOOD), 5, 0.3F));
		    PUMPKIN_CANDY = register("pumpkin_candy", new CandyItem(new Item.Settings().group(ItemGroup.FOOD), 2, 0.3F));
		    RARE_CANDY = register("rare_candy", new CandyItem(new Item.Settings().group(ItemGroup.FOOD), 1, 0.1F));
		    WINGED_STRAWBERRY_CANDY = register("winged_strawberry_candy", new CandyItem(new Item.Settings().group(ItemGroup.FOOD), 3, 0.3F));
        PUMPCOWN_SPAWN_EGG = register("pumpcown_spawn_egg", new SpawnEggItem(SpookyEntities.PUMPCOWN, 8273166, 14912029, new Item.Settings().group(ItemGroup.MISC)));
        BAKED_PUMPKIN_SEEDS = register("baked_pumpkin_seeds", new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).snack().build())));

        TINY_PUMPKIN = register("tiny_pumpkin", new BlockItem(SpookyBlocks.TINY_PUMPKIN, new Item.Settings().group(ItemGroup.MISC)));
  }

  private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, SpookyTime.id(name), item);
  }

  private SpookyItems() {
        // NO-OP
  }
}
