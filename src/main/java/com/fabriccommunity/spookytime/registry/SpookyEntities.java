package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.component.CandyComponent;
import com.fabriccommunity.spookytime.component.CandyComponent.VillagerCandyComponent;
import com.fabriccommunity.spookytime.entity.CrowEntity;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.fabriccommunity.spookytime.entity.SpookyCactusEntity;
import com.fabriccommunity.spookytime.entity.SpookyTreasureChestEntity;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.registry.Registry;

public class SpookyEntities {
	public static final ComponentType<CandyComponent> CANDY = ComponentRegistry.INSTANCE.registerIfAbsent(SpookyTime.id("candy"), CandyComponent.class);
	public static final EntityType<SpookyTreasureChestEntity> SPOOKY_TREASURE_CHEST = register("spooky_treasure_chest",
		FabricEntityTypeBuilder.<SpookyTreasureChestEntity>create(
			EntityCategory.MISC,
			SpookyTreasureChestEntity::new
		).size(EntityDimensions.fixed(.5f, .5f)).build()
	);
	public static final EntityType<PumpcownEntity> PUMPCOWN = register("pumpcown", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, PumpcownEntity::new).size(EntityDimensions.fixed(0.9F, 1.4F)).build());
	public static final EntityType<CrowEntity> CROW = register("crow", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, CrowEntity::new).size(EntityDimensions.fixed(0.5F, 0.9F)).build());
	public static final EntityType<SpookyCactusEntity> SPOOKY_CACTUS = register("spooky_cactus", FabricEntityTypeBuilder.create(EntityCategory.MISC, SpookyCactusEntity::new).size(EntityDimensions.changing(0.9F, 1.0F)).build());
	
	private SpookyEntities() {
		// NO-OP
	}
	
	public static void init() {
		EntityComponentCallback.event(VillagerEntity.class).register((player, components) -> components.put(CANDY, new VillagerCandyComponent()));
	}
	
	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entity) {
		return Registry.register(Registry.ENTITY_TYPE, SpookyTime.id(name), entity);
	}
}
