package com.fabriccommunity.thehallow.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;

import java.util.function.Supplier;

import static com.fabriccommunity.thehallow.registry.HallowedBlocks.INFUSION_ALTAR_BLOCK;
import static com.fabriccommunity.thehallow.registry.HallowedBlocks.INFUSION_PILLAR_BLOCK;

public class HallowedBlockEntities {
	public static final BlockEntityType<TinyPumpkinBlockEntity> TINY_PUMPKIN = register(
		"tiny_pumpkin",
		TinyPumpkinBlockEntity::new,
		HallowedBlocks.TINY_PUMPKIN,
		HallowedBlocks.WITCHED_PUMPKIN
	);
	
	public static final BlockEntityType<HallowedTreasureChestBlockEntity> SPOOKY_TREASURE_CHEST_BE = register(
		"hallowed_treasure_chest",
		HallowedTreasureChestBlockEntity::new,
		HallowedBlocks.SPOOKY_TREASURE_CHEST
	);
	
	public static final BlockEntityType<InfusionPillarBlockEntity> INFUSION_PILLAR_BLOCK_ENTITY = register(
		"infusion_pillar",
		InfusionPillarBlockEntity::new,
		INFUSION_PILLAR_BLOCK
	);
	
	public static final BlockEntityType<InfusionAltarBlockEntity> INFUSION_ALTAR_BLOCK_ENTITY = register(
		"infusion_altar",
		InfusionAltarBlockEntity::new,
		INFUSION_ALTAR_BLOCK
	);
	
	private HallowedBlockEntities() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY, TheHallow.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}
