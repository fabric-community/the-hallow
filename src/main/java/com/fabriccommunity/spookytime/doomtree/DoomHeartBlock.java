package com.fabriccommunity.spookytime.doomtree;

import com.fabriccommunity.spookytime.doomtree.treeheart.DoomHeartBlockEntity;

import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DoomHeartBlock extends BlockWithEntity {

	public DoomHeartBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new DoomHeartBlockEntity();
	}

	@Override
	public float calcBlockBreakingDelta(BlockState blockState, PlayerEntity player, BlockView blockView, BlockPos pos) {
		final ItemStack stack = player.inventory.getInvStack(player.inventory.selectedSlot);

		if (stack.isEmpty() || !FabricToolTags.AXES.contains(stack.getItem())) {
			return 0;
		}

		if (EnchantmentHelper.getLevel(Enchantments.SHARPNESS, stack) < 3) {
			return 0;
		}

		return super.calcBlockBreakingDelta(blockState, player, blockView, pos);
	}

	@Override
	public BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.MODEL;
	}
}
