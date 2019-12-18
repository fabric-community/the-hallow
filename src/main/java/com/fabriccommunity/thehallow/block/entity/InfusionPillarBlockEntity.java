package com.fabriccommunity.thehallow.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.registry.HallowedBlockEntities;

public class InfusionPillarBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	public ItemStack storedStack = ItemStack.EMPTY;
	
	public InfusionPillarBlockEntity() {
		super(HallowedBlockEntities.INFUSION_PILLAR_BLOCK_ENTITY);
	}
	
	public ItemStack putStack(ItemStack insertStack) {
		if (storedStack.isEmpty() && insertStack.getCount() >= 1) {
			storedStack = insertStack.split(1);
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
		if (entityTag.contains("stored_item")) {
			this.storedStack = new ItemStack(Registry.ITEM.getOrEmpty(new Identifier(entityTag.getString("stored_item"))).get());
		}
	}
	
	@Override
	public void fromClientTag(CompoundTag entityTag) {
		this.fromTag(entityTag);
	}
}
