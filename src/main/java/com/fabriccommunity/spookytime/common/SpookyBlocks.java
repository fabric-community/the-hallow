package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.block.DeceasedGrassBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks
{
    public static Block DECEASED_DIRT;
    public static Block DECEASED_GRASS_BLOCK;
    public static Block DECEASED_SAND;
    public static Block DECEASED_GRAVEL;

    public static void init() {
        DECEASED_DIRT = register("deceased_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.PURPLE).build()));
        DECEASED_GRASS_BLOCK = register("deceased_grass_block", new DeceasedGrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).materialColor(MaterialColor.PURPLE).build()));
        DECEASED_SAND = register("deceased_sand", new FallingBlock(FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.PURPLE).build()));
        DECEASED_GRAVEL = register("deceased_gravel", new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).materialColor(MaterialColor.PURPLE).build()));
    }

    private static Block register(String name, Block item)
    {
        return Registry.register(Registry.BLOCK, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyBlocks() {
        // NO-OP
    }
}
