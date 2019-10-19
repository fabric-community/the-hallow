package com.fabriccommunity.thehallow.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScytheItem extends SwordItem {
	public ScytheItem(ToolMaterial material, int damage, float speed, Settings settings) {
		super(material, damage, speed, settings);
	}
	
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		if (!target.isAlive()) {
			if (target instanceof PlayerEntity) {
				// give player soul
			} else if (target instanceof WitherEntity) {
				// give withered soul
			} else {
				EntityCategory category = target.getType().getCategory();
				switch (category) {
					case MONSTER:
						// give monster soul
						break;
					case CREATURE:
					case WATER_CREATURE:
					case AMBIENT:
						// give creature soul
						break;
					default:
						break;
				}
			}
		}
		return super.postHit(stack, target, user);
	}
	
	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity user) {
		if (state.getBlock() == Blocks.SOUL_SAND) {
			// give random soul
		}
		return super.postMine(stack, world, state, pos, user);
	}
}
