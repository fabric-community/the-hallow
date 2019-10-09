package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;

public class InfusionPillarBlockEntity extends BlockEntity {
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
}
