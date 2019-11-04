package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.component.CandyComponent;
import com.fabriccommunity.thehallow.component.CandyComponent.VillagerCandyComponent;
import com.fabriccommunity.thehallow.entity.CrowEntity;
import com.fabriccommunity.thehallow.entity.CultistEntity;
import com.fabriccommunity.thehallow.entity.MummyEntity;
import com.fabriccommunity.thehallow.entity.PumpcownEntity;
import com.fabriccommunity.thehallow.entity.RestlessCactusEntity;
import com.fabriccommunity.thehallow.mixin.SpawnRestrictionInvoker;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;

public class HallowedEntities {
	public static final ComponentType<CandyComponent> CANDY = ComponentRegistry.INSTANCE.registerIfAbsent(TheHallow.id("candy"), CandyComponent.class);
	public static final EntityType<HallowedTreasureChestEntity> HALLOWED_TREASURE_CHEST = register("hallowed_treasure_chest",
		FabricEntityTypeBuilder.<HallowedTreasureChestEntity>create(
			EntityCategory.MISC,
			HallowedTreasureChestEntity::new
		).size(EntityDimensions.fixed(.5f, .5f)).build()
	);
	public static final EntityType<PumpcownEntity> PUMPCOWN = register("pumpcown", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, PumpcownEntity::new).size(EntityDimensions.fixed(0.9F, 1.4F)).build());
	public static final EntityType<CrowEntity> CROW = register("crow", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, CrowEntity::new).size(EntityDimensions.fixed(0.5F, 0.9F)).build());
	public static final EntityType<RestlessCactusEntity> RESTLESS_CACTUS = register("restless_cactus", FabricEntityTypeBuilder.create(EntityCategory.MISC, RestlessCactusEntity::new).size(EntityDimensions.changing(0.9F, 1.0F)).build());
	public static final EntityType<MummyEntity> MUMMY = register("mummy", FabricEntityTypeBuilder.create(EntityCategory.MONSTER, MummyEntity::new).size(EntityDimensions.fixed(0.7F, 2.3F)).build());
	public static final EntityType<CultistEntity> CULTIST = register("cultist", FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CultistEntity::new).size(EntityDimensions.fixed(0.6F, 1.95F)).build());
	
	private HallowedEntities() {
		// NO-OP
	}
	
	public static void init() {
		@SuppressWarnings("unused") Object classloading = SpawnRestriction.class;
		SpawnRestrictionInvoker.invokeSetRestrictions(HallowedEntities.MUMMY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::method_20680);
		EntityComponentCallback.event(VillagerEntity.class).register((player, components) -> components.put(CANDY, new VillagerCandyComponent()));
	}
	
	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entity) {
		return Registry.register(Registry.ENTITY_TYPE, TheHallow.id(name), entity);
	}
}
