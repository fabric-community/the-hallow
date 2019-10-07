package com.fabriccommunity.spookytime.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.spookytime.entity.SpookyTreasureChestBlockEntity;

import java.util.function.Supplier;

public class SpookyBlockEntities {
	public static final BlockEntityType<TinyPumpkinBlockEntity> TINY_PUMPKIN = register(
		"tiny_pumpkin",
		TinyPumpkinBlockEntity::new,
		SpookyBlocks.TINY_PUMPKIN,
		SpookyBlocks.WITCHED_PUMPKIN
	);
	
	public static final BlockEntityType<SpookyTreasureChestBlockEntity> SPOOKY_TREASURE_CHEST_BE = register(
		"spooky_treasure_chest",
		SpookyTreasureChestBlockEntity::new,
		SpookyBlocks.SPOOKY_TREASURE_CHEST
	);
	
	private SpookyBlockEntities() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY, SpookyTime.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}
