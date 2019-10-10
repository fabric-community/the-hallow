package com.fabriccommunity.spookytime.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.registry.SpookySounds;

public class SpookyTrumpetItem extends Item {
	public SpookyTrumpetItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		
		float soundPitch = (((player.pitch - 90) * 1) / -90);
		
		if (player.isSneaking()) {
			player.playSound(SpookySounds.MEGALADOOT, 0.8f, 1.0f);
		} else {
			player.playSound(SpookySounds.DOOT, 0.8f, soundPitch);
		}
		
		return new TypedActionResult<>(ActionResult.SUCCESS, stack);
	}
}
