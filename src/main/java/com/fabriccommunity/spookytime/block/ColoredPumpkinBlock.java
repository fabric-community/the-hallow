package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ColoredPumpkinBlock extends PumpkinBlock {
	
	private PumpkinColor color;
	
	public ColoredPumpkinBlock(Settings blockSettings, PumpkinColor color) {
		super(blockSettings);
		this.color = color;
	}
	
	public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);

        if (itemStack_1.getItem() == Items.SHEARS) {
            if (!world_1.isClient) {
                Direction direction_1 = blockHitResult_1.getSide();
                Direction direction_2 = direction_1.getAxis() == Direction.Axis.Y ? playerEntity_1.getHorizontalFacing().getOpposite() : direction_1;

                world_1.playSound((PlayerEntity) null, blockPos_1, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world_1.setBlockState(blockPos_1, (BlockState) SpookyBlocks.CARVED_PUMPKIN_COLORS.get(this.color).getDefaultState().with(CarvedPumpkinBlock.FACING, direction_2), 11);
                ItemEntity itemEntity_1 = new ItemEntity(world_1, (double) blockPos_1.getX() + 0.5D + (double) direction_2.getOffsetX() * 0.65D, (double) blockPos_1.getY() + 0.1D, (double) blockPos_1.getZ() + 0.5D + (double) direction_2.getOffsetZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));

                itemEntity_1.setVelocity(0.05D * (double) direction_2.getOffsetX() + world_1.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction_2.getOffsetZ() + world_1.random.nextDouble() * 0.02D);
                world_1.spawnEntity(itemEntity_1);
                itemStack_1.damage(1, (LivingEntity) playerEntity_1, (playerEntity_1x) -> {
                    playerEntity_1x.sendToolBreakStatus(hand_1);
                });
            }

            return true;
        } else {
            return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
        }
    }
	
	public static enum PumpkinColor {
		RED, YELLOW, WHITE, BLUE, TAN, WITCHED, GREEN_STRIPED;
	}
}
