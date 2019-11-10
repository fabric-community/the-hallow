package com.fabriccommunity.thehallow.item;

import java.util.List;

import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PumpkinRing extends Item implements ITrinket {
	public PumpkinRing(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.contains(SlotGroups.HAND) && slot.equals(Slots.RING);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		return ITrinket.equipTrinket(player, hand);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext context) {
		list.add(new TranslatableText("text.thehallow.pumpkin_ring").formatted(Formatting.GRAY));
		super.appendTooltip(itemStack, world, list, context);
	}
}
