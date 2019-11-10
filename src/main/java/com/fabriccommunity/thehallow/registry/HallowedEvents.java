package com.fabriccommunity.thehallow.registry;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.ActionResult;
import net.minecraft.world.loot.condition.RandomChanceLootCondition;
import net.minecraft.world.loot.entry.ItemEntry;

import com.fabriccommunity.thehallow.event.WitchTickCallback;

public class HallowedEvents {
	public static void init() {
		WitchTickCallback.EVENT.register((witch) -> {
			if (!witch.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
				if (witch.getEntityWorld().hasRain(witch.getBlockPos().up())) {
					witch.damage(DamageSource.DROWN, 4.0F);
				} else if (witch.checkWaterState() && !witch.updateMovementInFluid(HallowedTags.Fluids.WITCH_WATER)) {
					witch.damage(DamageSource.DROWN, 4.0F);
				}
			}
			return ActionResult.PASS;
		});
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
			if (id.equals(EntityType.WITCH.getLootTableId())) {
				supplier.withPool(FabricLootPoolBuilder.builder().withCondition(RandomChanceLootCondition.builder(0.01f)).withEntry(ItemEntry.builder(HallowedItems.GOLDEN_CANDY_CORN)));
			}
		}));
	}
}
