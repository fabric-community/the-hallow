package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.block.InfusionAltarBlock;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyInfusion;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfusionAltarBlockEntity extends BlockEntity implements Tickable  {
	public Map<BlockPos, InfusionPillarBlockEntity> linkedPillars = new HashMap<BlockPos, InfusionPillarBlockEntity>();

	public List<ItemStack> stacksToDraw = new ArrayList<>();

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

	public static ItemStack craftRecipe(List<ItemStack> ingredients) {
		ItemStack outputStack = SpookyInfusion.fromInput(ingredients).getOutput();
		return outputStack == null ? ItemStack.EMPTY : outputStack.copy();
	}

	@Override
	public void tick() {
		InfusionAltarBlock altarBlock = (InfusionAltarBlock)this.getWorld().getBlockState(this.getPos()).getBlock();
		if (altarBlock.)

	}
}
