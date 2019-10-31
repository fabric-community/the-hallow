package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.InfestedBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class HallowedInfestedBlock extends InfestedBlock {

	public HallowedInfestedBlock(Block block, Settings settings) {
		super(block, settings);
	}
	
	@Override
	public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
		if (!world.isClient && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			BatEntity bat = (BatEntity) EntityType.BAT.create(world);
			bat.setPositionAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
			world.spawnEntity(bat);
			bat.playSpawnEffects();
		}
	}
}
