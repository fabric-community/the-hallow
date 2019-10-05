package com.fabriccommunity.spookytime.item;

import dev.emi.trinkets.api.ITrinket;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class PumpkinRing extends Item implements ITrinket {
	public PumpkinRing(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext context) {
		list.add(new TranslatableText("tooltip.spookytime.pumpkin_ring").formatted(Formatting.GRAY));
		super.appendTooltip(itemStack, world, list, context);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.contains("hand") && slot.equals("ring");
	}
}
