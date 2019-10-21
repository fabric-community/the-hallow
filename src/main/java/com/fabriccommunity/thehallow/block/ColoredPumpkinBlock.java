package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.entity.ItemEntity;
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

	public ColoredPumpkinBlock(Settings settings, PumpkinColor color) {
		super(settings);
		this.color = color;
	}

	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		ItemStack stack = playerEntity.getStackInHand(hand);

		if (stack.getItem() == Items.SHEARS) {
			if (!world.isClient) {
				Direction side = blockHitResult.getSide();
				Direction facingTowardPlayer = side.getAxis() == Direction.Axis.Y ? playerEntity.getHorizontalFacing().getOpposite() : side;

				world.playSound(null, blockPos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockState(blockPos, HallowedBlocks.CARVED_PUMPKIN_COLORS.get(this.color).getDefaultState().with(CarvedPumpkinBlock.FACING, facingTowardPlayer), 11);
				ItemEntity itemEntity = new ItemEntity(world, blockPos.getX() + 0.5D + facingTowardPlayer.getOffsetX() * 0.65D, blockPos.getY() + 0.1D, blockPos.getZ() + 0.5D + facingTowardPlayer.getOffsetZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));

				itemEntity.setVelocity(0.05D * (double) facingTowardPlayer.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) facingTowardPlayer.getOffsetZ() + world.random.nextDouble() * 0.02D);
				world.spawnEntity(itemEntity);
				stack.damage(1, playerEntity, (playerEntityVar) -> {
					playerEntityVar.sendToolBreakStatus(hand);
				});
			}

			return true;
		} else {
			return super.activate(blockState, world, blockPos, playerEntity, hand, blockHitResult);
		}
	}

	public enum PumpkinColor {
		RED, YELLOW, WHITE, BLUE, TAN, WITCHED, RAINBOW;
	}
}
