package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.block.InfusionAltarBlock;
import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyInfusion;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfusionAltarBlockEntity extends BlockEntity {
	public Map<BlockPos, InfusionPillarBlockEntity> linkedPillars = new HashMap<BlockPos, InfusionPillarBlockEntity>();

	public ItemStack storedStack = ItemStack.EMPTY;

	public InfusionAltarBlockEntity() {
		super(SpookyBlocks.INFUSION_ALTAR_BLOCK_ENTITY);
	}

	public void addPillar(BlockPos blockPos, InfusionPillarBlockEntity pillarEntity) {
		linkedPillars.put(blockPos, pillarEntity);
	}

	public void removePillar(BlockPos blockPos, InfusionPillarBlockEntity pillarEntity) {
		linkedPillars.remove(blockPos);
	}

	public static ItemStack craftRecipe(Ingredient ingredients) {
		ItemStack result = InfusionRecipe.fromInput(ingredients).getOutput();
		return result == null ? ItemStack.EMPTY : result.copy();
	}
}
