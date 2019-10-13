package com.fabriccommunity.spookytime.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FernBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import java.util.Random;

public class SpookyFernBlock extends FernBlock {
	public SpookyFernBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void grow(World world, Random random, BlockPos pos, BlockState state) {
		TallPlantBlock tallEerie = (TallPlantBlock) SpookyBlocks.TALL_EERIE_GRASS;
		if (tallEerie.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
			tallEerie.placeAt(world, pos, 2);
		}
	}
}
