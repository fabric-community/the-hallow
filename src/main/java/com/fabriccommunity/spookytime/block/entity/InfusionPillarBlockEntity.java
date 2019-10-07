package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfusionPillarBlockEntity extends BlockEntity {
	public ItemStack storedStack = null;

	public InfusionPillarBlockEntity() {
		super(SpookyBlocks.INFUSION_PILLAR_BLOCK_ENTITY);
	}

	public ItemStack putStack(ItemStack itemStack) {
		if (storedStack == null) {
			storedStack = itemStack.copy();
			itemStack = itemStack.EMPTY;
		} else if (storedStack.getItem() == itemStack.getItem()
				&& storedStack.getTag()  == itemStack.getTag()) {
			int fits = storedStack.getMaxCount() - itemStack.getCount();
			if (fits > 0) {
				storedStack.setCount(storedStack.getCount() + fits);
				itemStack.setCount(itemStack.getCount() - fits);
			}
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
