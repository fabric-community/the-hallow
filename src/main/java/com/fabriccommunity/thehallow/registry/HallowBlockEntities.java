package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

import static com.fabriccommunity.thehallow.registry.HallowBlocks.INFUSION_ALTAR_BLOCK;
import static com.fabriccommunity.thehallow.registry.HallowBlocks.INFUSION_PILLAR_BLOCK;

public class HallowBlockEntities {
	public static final BlockEntityType<TinyPumpkinBlockEntity> TINY_PUMPKIN = register(
		"tiny_pumpkin",
		TinyPumpkinBlockEntity::new,
		HallowBlocks.TINY_PUMPKIN,
		HallowBlocks.TINY_WITCHED_PUMPKIN
	);
	
	public static final BlockEntityType<HallowedTreasureChestBlockEntity> HALLOWED_TREASURE_CHEST_BE = register(
		"spooky_treasure_chest",
		HallowedTreasureChestBlockEntity::new,
		HallowBlocks.HALLOWED_TREASURE_CHEST
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
	
	private HallowBlockEntities() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY, TheHallow.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}
