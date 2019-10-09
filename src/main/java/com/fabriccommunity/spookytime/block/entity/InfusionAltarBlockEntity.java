package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
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
