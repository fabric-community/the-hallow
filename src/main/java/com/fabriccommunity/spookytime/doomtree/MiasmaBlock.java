package com.fabriccommunity.spookytime.doomtree;

import com.fabriccommunity.spookytime.doomtree.treeheart.DoomTreeTracker;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MiasmaBlock extends Block {

	public MiasmaBlock() {
		super(FabricBlockSettings.copy(Blocks.AIR).build());
	}

	@Override
	public BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return VoxelShapes.empty();
	}
	
	@Override
	public void onBlockRemoved(BlockState myState, World world, BlockPos blockPos, BlockState newState, boolean someFlag) {
		super.onBlockRemoved(myState, world, blockPos, newState, someFlag);
		
		if (!world.isClient) {
			final FluidState fluidState = newState.getFluidState();
			if(fluidState != null && !fluidState.isStill()) {
				//TODO: handle flowing fluids as separate queue
				
			} else {
				DoomTreeTracker.reportBreak(world, blockPos, false);
			}
		}
	}

	@Override
	public boolean isAir(BlockState blockState) {
		return true;
	}
}
