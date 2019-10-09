package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.world.loot.condition.RandomChanceLootCondition;
import net.minecraft.world.loot.entry.ItemEntry;

public class SpookyEvents {

	private SpookyEvents() {
		// NO-OP
	}

	public static void init() {
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
			if (id.equals(EntityType.WITCH.getLootTableId())) {
				supplier.withPool(FabricLootPoolBuilder.builder().withCondition(RandomChanceLootCondition.builder(0.01f)).withEntry(ItemEntry.builder(SpookyItems.GOLDEN_CANDY_CORN)));
			}
		}));
	}
}
