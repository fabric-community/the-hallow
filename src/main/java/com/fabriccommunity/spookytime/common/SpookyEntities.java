package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.common.CandyComponent.VillagerCandyComponnt;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyEntities
{
	public static ComponentType<CandyComponent> CANDY = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(SpookyTime.MODID + ":candy"), CandyComponent.class);

	public static EntityType<PumpcownEntity> PUMPCOWN;

    public static void init(){
		EntityComponentCallback.event(VillagerEntity.class).register((player, components) -> components.put(CANDY, new VillagerCandyComponnt()));
        PUMPCOWN = register("pumpcown", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, PumpcownEntity::new).size(EntityDimensions.fixed(0.9F, 1.4F)).build());
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> blockEntity) {
        return Registry.register(Registry.BLOCK_ENTITY, new Identifier(SpookyTime.MODID, name), blockEntity);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entity) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(SpookyTime.MODID, name), entity);
    }

    private SpookyEntities() {
        // NO-OP
    }
}
