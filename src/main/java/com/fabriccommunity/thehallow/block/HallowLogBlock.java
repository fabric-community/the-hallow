package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HallowLogBlock extends LogBlock {
	private Block stripped;
	
	public HallowLogBlock(MaterialColor topColor, Settings settings) {
		super(topColor, settings);
	}
	
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.isEmpty() || !(stack.getItem() instanceof MiningToolItem)) {
			return false;
		}
		
		MiningToolItem tool = (MiningToolItem) stack.getItem();
		if (stripped != null && (tool.isEffectiveOn(state) || tool.getMiningSpeed(stack, state) > 1.0F)) {
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isClient) {
				BlockState target = stripped.getDefaultState().with(LogBlock.AXIS, state.get(LogBlock.AXIS));
				world.setBlockState(pos, target);
				stack.damage(1, player, consumedPlayer -> consumedPlayer.sendToolBreakStatus(hand));
			}
			return true;
		}
		return false;
	}
	
	public void setStripped(Block stripped) {
		this.stripped = stripped;
	}
}
