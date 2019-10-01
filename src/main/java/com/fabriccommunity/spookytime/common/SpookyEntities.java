package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyEntities
{


    public static void init() {
        // NO-OP
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> be)
    {
        return Registry.register(Registry.BLOCK_ENTITY, new Identifier(SpookyTime.MODID, name), be);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> be)
    {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(SpookyTime.MODID, name), be);
    }

    private SpookyEntities() {
        // NO-OP
    }
}
