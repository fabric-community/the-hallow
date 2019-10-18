package com.fabriccommunity.spookytime.doomtree;

import com.fabriccommunity.spookytime.doomtree.treeheart.DoomTreeTracker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DoomedBlock extends Block {

	public DoomedBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onBlockRemoved(BlockState myState, World world, BlockPos blockPos, BlockState newState, boolean someFlag) {
		super.onBlockRemoved(myState, world, blockPos, newState, someFlag);

		if (!world.isClient) {
			DoomTreeTracker.reportBreak(world, blockPos, false);
		}
	}

	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState blockState, BlockEntity blockEntity, ItemStack toolStack) {
		super.afterBreak(world, player, pos, blockState, blockEntity, toolStack);

		if (!world.isClient && !toolStack.getItem().isIn(DoomTree.WARDED_ITEM_TAG)) {
			float extraExhaustion = 0.01F;
			// if using a tool, take extra durability.  If not, then extra doom exposure for player
			if (toolStack.getItem().isDamageable()) {
				player.getMainHandStack().damage(3, player, p -> {
					p.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
				});
			} else {
				extraExhaustion += 0.01F;
				//TODO: doom for player
			}
			player.addExhaustion(extraExhaustion);
		}
	}


}
