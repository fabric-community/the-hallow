package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfusionPillarBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	public ItemStack storedStack = null;

	public InfusionPillarBlockEntity() {
		super(SpookyBlockEntities.INFUSION_PILLAR_BLOCK_ENTITY);
	}

	public ItemStack putStack(ItemStack insertStack) {
		if (storedStack == null && insertStack.getCount() >= 1) {
			storedStack = new ItemStack(insertStack.getItem(), 1);
			insertStack.decrement(1);
		}
		return insertStack;
	}

	public ItemStack takeStack() {
		if (storedStack != null) {
			ItemStack takeStack = storedStack.copy();
			storedStack = null;
			return takeStack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag entityTag) {
		if (this.storedStack != null) {
			entityTag.putString("stored_item", this.storedStack.getItem().toString());
		}
		return entityTag;
	}

	@Override
	public CompoundTag toClientTag(CompoundTag entityTag) {
		return this.toTag(entityTag);
	}

	@Override
	public void fromTag(CompoundTag entityTag) {
		if (entityTag.containsKey("stored_item")) {
			this.storedStack = new ItemStack(Registry.ITEM.getOrEmpty(new Identifier(entityTag.getString("stored_item"))).get());
		}
	}

	@Override
	public void fromClientTag(CompoundTag entityTag) {
		this.fromTag(entityTag);
	}
}
