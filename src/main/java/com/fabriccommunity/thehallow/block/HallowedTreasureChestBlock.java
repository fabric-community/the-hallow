package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;

public class HallowedTreasureChestBlock extends HorizontalFacingBlock implements BlockEntityProvider {
	private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
	
	public HallowedTreasureChestBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		if (hand == Hand.MAIN_HAND) {
			float x = pos.getX() + .49f;
			float y = pos.getY();
			float z = pos.getZ() + .51f;
			
			HallowedTreasureChestEntity entity = new HallowedTreasureChestEntity(world, x, y, z, true, blockState.get(FACING).asRotation());
			entity.setPosition(x, y, z);
			world.spawnEntity(entity);
			
			return ActionResult.SUCCESS;
		}
		
		return ActionResult.PASS;
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView var1) {
		return new HallowedTreasureChestBlockEntity();
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> factory) {
		factory.add(FACING);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.INVISIBLE;
	}
	
	//@Override
	//public BlockRenderLayer getRenderLayer() {
	//	return BlockRenderLayer.CUTOUT;
	//}
	//FIXME HallowedTreasureChest render layer: CUTOUT
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return SHAPE;
	}
}
