package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class SpookyBlockEntities {
	public static final BlockEntityType<TinyPumpkinBlockEntity> TINY_PUMPKIN = register("tiny_pumpkin", TinyPumpkinBlockEntity::new, SpookyBlocks.TINY_PUMPKIN, SpookyBlocks.WITCHED_PUMPKIN);
	
	private SpookyBlockEntities() {
		// NO-OP
	}
	
	public static void init() {
	
	}
	
	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY, SpookyTime.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}
