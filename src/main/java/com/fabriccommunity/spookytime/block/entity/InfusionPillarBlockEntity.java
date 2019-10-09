package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;

public class InfusionPillarBlockEntity extends BlockEntity {
	public ItemStack storedStack = null;

	public InfusionPillarBlockEntity() {
		super(SpookyBlockEntities.INFUSION_PILLAR_BLOCK_ENTITY);
	}

	public ItemStack putStack(ItemStack itemStack) {
		if (storedStack == null && itemStack.getCount() >= 1) {
			storedStack = new ItemStack(itemStack.getItem(), 1);
			itemStack.decrement(1);
		}
		return itemStack;
	}

	public ItemStack takeStack() {
		if (storedStack != null) {
			ItemStack returnStack = storedStack.copy();
			storedStack = null;
			return returnStack;
		} else {
			return ItemStack.EMPTY;
		}
	}
}
