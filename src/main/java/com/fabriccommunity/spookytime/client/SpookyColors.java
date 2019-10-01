package com.fabriccommunity.spookytime.client;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyItems;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;

public class SpookyColors {
    public static void init() {
        ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 0x20003B, SpookyBlocks.DECEASED_GRASS_BLOCK);

        ColorProviderRegistry.ITEM.register((item, layer) -> 0x20003B, SpookyItems.DECEASED_GRASS_BLOCK);
    }
}
