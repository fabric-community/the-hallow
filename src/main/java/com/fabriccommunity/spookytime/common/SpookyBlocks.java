package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.block.TinyPumpkinBlock;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks
{


    public static Block TINY_PUMPKIN;

    public static void init() {
        register("tiny_pumpkin", TINY_PUMPKIN = new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build())); 
        // NO-OP
    }

    private static Block register(String name, Block item)
    {
        return Registry.register(Registry.BLOCK, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyBlocks() {
        // NO-OP
    }
}
