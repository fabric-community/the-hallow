package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks
{


	public static void init() {
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
