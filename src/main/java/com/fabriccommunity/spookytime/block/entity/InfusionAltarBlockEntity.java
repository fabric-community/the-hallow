package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class InfusionAltarBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	public Map<BlockPos, InfusionPillarBlockEntity> linkedPillars = new HashMap<BlockPos, InfusionPillarBlockEntity>();

	public ItemStack storedStack = ItemStack.EMPTY;

	public InfusionAltarBlockEntity() {
		super(SpookyBlockEntities.INFUSION_ALTAR_BLOCK_ENTITY);
	}

	public void addPillar(BlockPos blockPos, InfusionPillarBlockEntity pillarEntity) {
		linkedPillars.put(blockPos, pillarEntity);
	}

	public void removePillar(BlockPos blockPos, InfusionPillarBlockEntity pillarEntity) {
		linkedPillars.remove(blockPos);
	}

	public ItemStack putStack(ItemStack insertStack) {
		if (storedStack.isEmpty() && insertStack.getCount() >= 1) {
			storedStack = new ItemStack(insertStack.getItem(), 1);
			insertStack.decrement(1);
		}
		return insertStack;
	}

	public ItemStack takeStack() {
		if (!storedStack.isEmpty()) {
			ItemStack takeStack = storedStack.copy();
			storedStack = ItemStack.EMPTY;
			return takeStack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag entityTag) {
		super.toTag(entityTag);
		if (!storedStack.isEmpty()) {
			entityTag.putString("stored_item", Registry.ITEM.getId(storedStack.getItem()).toString());
		}
		return entityTag;
	}

	@Override
	public CompoundTag toClientTag(CompoundTag entityTag) {
		return this.toTag(entityTag);
	}

	@Override
	public void fromTag(CompoundTag entityTag) {
		super.fromTag(entityTag);
		if (entityTag.containsKey("stored_item")) {
			this.storedStack = new ItemStack(Registry.ITEM.getOrEmpty(new Identifier(entityTag.getString("stored_item"))).get());
		}
	}

	@Override
	public void fromClientTag(CompoundTag entityTag) {
		this.fromTag(entityTag);
	}
}
