package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

public class HallowedMushroomPlantBlock extends MushroomPlantBlock {
	public HallowedMushroomPlantBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		if (super.canPlaceAt(state, world, pos)) return true;
		BlockPos downPos = pos.down();
		BlockState downState = world.getBlockState(downPos);
		Block downBlock = downState.getBlock();
		return (downBlock == HallowedBlocks.DECEASED_GRASS_BLOCK || downBlock == HallowedBlocks.DECEASED_DIRT || downBlock == HallowedBlocks.DECEASED_MOSS);
	}
	
	@Override
	public boolean isFertilizable(BlockView view, BlockPos pos, BlockState state, boolean bool) {
		return false;
	}
}
