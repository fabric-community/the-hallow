package com.fabriccommunity.spookytime.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenCandyCornItem extends CandyItem {
	public GoldenCandyCornItem(Settings settings, int hunger, float saturation) {
		super(settings, hunger, saturation);
	}
	
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (user instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) user;
			stack.getOrCreateTag().putBoolean("Eating", true);
			player.getItemCooldownManager().set(this, 40);
		}
		return super.finishUsing(stack, world, user);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			CompoundTag tag = stack.getOrCreateTag();
			if ((selected || slot == 40) && player.getHungerManager().isNotFull()) {
				if (!player.getItemCooldownManager().isCoolingDown(this) && tag.getBoolean("Eating")) {
					player.eatFood(world, stack);
					player.getItemCooldownManager().set(this, 40);
				}
			} else {
				if (tag.getBoolean("Eating")) tag.putBoolean("Eating", false);
			}
		}
	}
	
	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 5;
	}
	
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext ctx) {
		tooltip.add(new TranslatableText("text.spookytime.candycorn.0").formatted(Formatting.GOLD));
		tooltip.add(new TranslatableText("text.spookytime.candycorn.1").formatted(Formatting.GOLD));
	}
}
