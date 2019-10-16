package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

public class SpookyMushroomPlantBlock extends MushroomPlantBlock {
	public SpookyMushroomPlantBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		if(super.canPlaceAt(state, world, pos)) return true;
		BlockPos downPos = pos.down();
		BlockState downState = world.getBlockState(downPos);
		Block downBlock = downState.getBlock();
		return (downBlock == SpookyBlocks.DECEASED_GRASS_BLOCK || downBlock == SpookyBlocks.DECEASED_DIRT || downBlock == SpookyBlocks.DECEASED_MOSS);
	}
	
	@Override
	public boolean isFertilizable(BlockView view, BlockPos pos, BlockState state, boolean bool) {
		return false;
	}
}
