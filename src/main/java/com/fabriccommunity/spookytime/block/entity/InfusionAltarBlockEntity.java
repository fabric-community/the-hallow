package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class  InfusionAltarBlockEntity extends BlockEntity {
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
