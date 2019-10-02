package com.fabriccommunity.spookytime.doomtree;

import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.*;

import com.fabriccommunity.spookytime.registry.SpookyItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
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
		
		return false;
		
		//			
		//			int int_1 = (Integer)blockState.get(LEVEL);
		//			Item item_1 = stack.getItem();
		//			if (item_1 == Items.WATER_BUCKET) {
		//				if (int_1 < 3 && !world.isClient) {
		//					if (!player.abilities.creativeMode) {
		//						player.setStackInHand(hand, new ItemStack(Items.BUCKET));
		//					}
		//
		//					player.incrementStat(Stats.FILL_CAULDRON);
		//					this.setLevel(world, pos, blockState, 3);
		//					world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
		//				}
		//
		//				return true;
		//			} else if (item_1 == Items.BUCKET) {
		//				if (int_1 == 3 && !world.isClient) {
		//					if (!player.abilities.creativeMode) {
		//						stack.decrement(1);
		//						if (stack.isEmpty()) {
		//							player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
		//						} else if (!player.inventory.insertStack(new ItemStack(Items.WATER_BUCKET))) {
		//							player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
		//						}
		//					}
		//
		//					player.incrementStat(Stats.USE_CAULDRON);
		//					this.setLevel(world, pos, blockState, 0);
		//					world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		//				}
		//
		//				return true;
		//			} else {
		//				ItemStack itemStack_4;
		//				if (item_1 == Items.GLASS_BOTTLE) {
		//					if (int_1 > 0 && !world.isClient) {
		//						if (!player.abilities.creativeMode) {
		//							itemStack_4 = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
		//							player.incrementStat(Stats.USE_CAULDRON);
		//							stack.decrement(1);
		//							if (stack.isEmpty()) {
		//								player.setStackInHand(hand, itemStack_4);
		//							} else if (!player.inventory.insertStack(itemStack_4)) {
		//								player.dropItem(itemStack_4, false);
		//							} else if (player instanceof ServerPlayerEntity) {
		//								((ServerPlayerEntity)player).openContainer((Container)player.playerContainer);
		//							}
		//						}
		//
		//						world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		//						this.setLevel(world, pos, blockState, int_1 - 1);
		//					}
		//
		//					return true;
		//				} else if (item_1 == Items.POTION && PotionUtil.getPotion(stack) == Potions.WATER) {
		//					if (int_1 < 3 && !world.isClient) {
		//						if (!player.abilities.creativeMode) {
		//							itemStack_4 = new ItemStack(Items.GLASS_BOTTLE);
		//							player.incrementStat(Stats.USE_CAULDRON);
		//							player.setStackInHand(hand, itemStack_4);
		//							if (player instanceof ServerPlayerEntity) {
		//								((ServerPlayerEntity)player).openContainer((Container)player.playerContainer);
		//							}
		//						}
		//
		//						world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
		//						this.setLevel(world, pos, blockState, int_1 + 1);
		//					}
		//
		//					return true;
		//				} else {
		//					if (int_1 > 0 && item_1 instanceof DyeableItem) {
		//						DyeableItem dyeableItem_1 = (DyeableItem)item_1;
		//						if (dyeableItem_1.hasColor(stack) && !world.isClient) {
		//							dyeableItem_1.removeColor(stack);
		//							this.setLevel(world, pos, blockState, int_1 - 1);
		//							player.incrementStat(Stats.CLEAN_ARMOR);
		//							return true;
		//						}
		//					}
		//
		//					if (int_1 > 0 && item_1 instanceof BannerItem) {
		//						if (BannerBlockEntity.getPatternCount(stack) > 0 && !world.isClient) {
		//							itemStack_4 = stack.copy();
		//							itemStack_4.setCount(1);
		//							BannerBlockEntity.loadFromItemStack(itemStack_4);
		//							player.incrementStat(Stats.CLEAN_BANNER);
		//							if (!player.abilities.creativeMode) {
		//								stack.decrement(1);
		//								this.setLevel(world, pos, blockState, int_1 - 1);
		//							}
		//
		//							if (stack.isEmpty()) {
		//								player.setStackInHand(hand, itemStack_4);
		//							} else if (!player.inventory.insertStack(itemStack_4)) {
		//								player.dropItem(itemStack_4, false);
		//							} else if (player instanceof ServerPlayerEntity) {
		//								((ServerPlayerEntity)player).openContainer((Container)player.playerContainer);
		//							}
		//						}
		//
		//						return true;
		//					} else if (int_1 > 0 && item_1 instanceof BlockItem) {
		//						Block block_1 = ((BlockItem)item_1).getBlock();
		//						if (block_1 instanceof ShulkerBoxBlock && !world.isClient()) {
		//							ItemStack itemStack_5 = new ItemStack(Blocks.SHULKER_BOX, 1);
		//							if (stack.hasTag()) {
		//								itemStack_5.setTag(stack.getTag().method_10553());
		//							}
		//
		//							player.setStackInHand(hand, itemStack_5);
		//							this.setLevel(world, pos, blockState, int_1 - 1);
		//							player.incrementStat(Stats.CLEAN_SHULKER_BOX);
		//						}
		//
		//						return true;
		//					} else {
		//						return false;
		//					}
	}
}
