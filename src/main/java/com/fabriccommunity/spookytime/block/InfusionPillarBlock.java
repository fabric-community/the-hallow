package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import javax.annotation.Nullable;

public class InfusionPillarBlock extends Block implements BlockEntityProvider {
	private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 12, 12);
	
	public InfusionPillarBlock(Block.Settings settings) {
		super(settings);
	}
	
	public InfusionAltarBlockEntity getAltar(World world, BlockPos blockPos) {
		for (Direction direction : HorizontalFacingBlock.FACING.getValues()) {
			BlockPos offsetPos = blockPos.offset(direction, 3);
			if (world.getBlockState(offsetPos).getBlock() == SpookyBlocks.INFUSION_ALTAR_BLOCK) {
				InfusionAltarBlockEntity altarEntity = (InfusionAltarBlockEntity) world.getBlockEntity(offsetPos);
				if (altarEntity != null) {
					return altarEntity;
				}
			}
		}
		return null;
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new InfusionPillarBlockEntity();
	}
	
	@Override
	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		InfusionPillarBlockEntity pillarEntity = (InfusionPillarBlockEntity) world.getBlockEntity(blockPos);
		if (pillarEntity != null) {
			if (playerEntity.getStackInHand(hand).isEmpty()) {
				playerEntity.inventory.insertStack(pillarEntity.takeStack());
			} else {
				playerEntity.setStackInHand(hand, pillarEntity.putStack(playerEntity.getStackInHand(hand)));
			}
		}
		return true;
	}
	
	@Override
	public void afterBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
		Block.dropStack(world, blockPos, ((InfusionPillarBlockEntity) blockEntity).storedStack);
		super.afterBreak(world, playerEntity, blockPos, blockState, blockEntity, itemStack);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		return SHAPE;
	}
}
