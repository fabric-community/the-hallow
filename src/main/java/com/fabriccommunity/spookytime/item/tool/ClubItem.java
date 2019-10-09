package com.fabriccommunity.spookytime.item.tool;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ClubItem extends SwordItem {
	public ClubItem(ToolMaterial material, int damage, float speed, Settings settings) {
		super(material, damage, speed, settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		return new TypedActionResult<>(ActionResult.SUCCESS, stack);
	}
}
