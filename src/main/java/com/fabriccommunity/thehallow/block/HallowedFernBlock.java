package com.fabriccommunity.thehallow.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.Random;

public class HallowedFernBlock extends FernBlock {
	public HallowedFernBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void grow(World world, Random random, BlockPos pos, BlockState state) {
		TallPlantBlock tallEerie = (TallPlantBlock) HallowedBlocks.TALL_EERIE_GRASS;
		if (tallEerie.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
			tallEerie.placeAt(world, pos, 2);
		}
	}
}
