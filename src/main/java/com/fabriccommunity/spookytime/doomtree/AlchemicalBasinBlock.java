package com.fabriccommunity.spookytime.doomtree;

import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.MAX_LEVEL;
import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.MODE_EMPTY;
import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.MODE_PRIMED_WATER;
import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.MODE_PRIMED_WITCHWATER;

import com.fabriccommunity.spookytime.registry.SpookyItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.BooleanBiFunction;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AlchemicalBasinBlock extends BlockWithEntity {
	private static final VoxelShape RAY_TRACE_SHAPE = createCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.union(createCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), createCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), RAY_TRACE_SHAPE), BooleanBiFunction.ONLY_FIRST);

	public AlchemicalBasinBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
		return OUTLINE_SHAPE;
	}

	@Override
	public boolean isOpaque(BlockState blockState_1) {
		return false;
	}

	@Override
	public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
		return RAY_TRACE_SHAPE;
	}

	@Override
	public BlockEntity createBlockEntity(BlockView var1) {
		return new AlchemicalBasinBlockEntity();
	}

	@Override
	public BlockRenderType getRenderType(BlockState blockState_1) {
		return BlockRenderType.MODEL;
	}

	/**
	 * This is a big hack in the interest of time - essentially a prototype implementation.
	 * If this sticks around will need to make a properly configurable handler with recipes.
	 */
	@Override
	public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		final ItemStack stack = player.getStackInHand(hand);

		if (stack.isEmpty()) {
			return true;
		};

		final BlockEntity be = world.getBlockEntity(pos);

		if (be == null || !(be instanceof  AlchemicalBasinBlockEntity)) {
			return true;
		}

		final AlchemicalBasinBlockEntity myBe = (AlchemicalBasinBlockEntity) be;
		final int mode = myBe.mode();
		final Item item = stack.getItem();
		final int limit = Math.max(0, MAX_LEVEL - myBe.level());

		if (mode == MODE_EMPTY) {
			if (item == SpookyItems.WITCH_WATER_BUCKET || item == Items.WATER_BUCKET) {
				if (!world.isClient) {
					if (!player.abilities.creativeMode) {
						player.setStackInHand(hand, new ItemStack(Items.BUCKET));
					}

					myBe.setState(item == Items.WATER_BUCKET ? MODE_PRIMED_WATER : MODE_PRIMED_WITCHWATER, 32);
					world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return true;
			}
		} 

		final int doomFuelValue = item == DoomTree.DOOM_FRAGMENT_ITEM || item  == DoomTree.DOOM_LEAF_ITEM ? 1 
				: item == DoomTree.DOOM_LOG_ITEM || item  == DoomTree.PLACED_DOOM_LOG_ITEM ? 4 : 0;
		
//		if (doomFuelValue > 0) {
//			if (limit > 0 && doomFuelValue <= limit && (mode == MODE_PRIMED_WATER || mode == MODE_BURNING)) {
//				final int maxConsumed = limit / doomFuelValue;
//
//			} else {
//				return false;
//			}
//		}


		return false;
	}
}
