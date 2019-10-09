package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class InfusionAltarBlockEntity extends BlockEntity {
	public Map<BlockPos, InfusionPillarBlockEntity> linkedPillars = new HashMap<BlockPos, InfusionPillarBlockEntity>();

	public ItemStack storedStack = null;

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
