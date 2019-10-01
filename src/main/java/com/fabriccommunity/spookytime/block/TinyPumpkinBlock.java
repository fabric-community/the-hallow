package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TinyPumpkinBlock extends HorizontalFacingBlock {


    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);    

    public TinyPumpkinBlock(Settings blockSettings) {
        super(blockSettings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return (BlockState)this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
    }
      

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
        return Y_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
    	return this.collidable ? blockState.getOutlineShape(blockView, blockPosition) : VoxelShapes.empty();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
        builder.add(HorizontalFacingBlock.FACING);
    }
}