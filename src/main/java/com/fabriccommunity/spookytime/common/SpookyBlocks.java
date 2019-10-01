package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks
{


    public static void init() {
        register("spooky_lantern", new Block(FabricBlockSettings.of(Material.REDSTONE_LAMP).breakByHand(true).lightLevel(5).ticksRandomly().build()){});
    }

    private static Block register(String name, Block item)
    {
        return Registry.register(Registry.BLOCK, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyBlocks() {
        // NO-OP
    }
}
