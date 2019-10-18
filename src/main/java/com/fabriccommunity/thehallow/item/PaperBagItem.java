package com.fabriccommunity.thehallow.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import dev.emi.trinkets.api.ITrinket;

import java.util.List;

public class PaperBagItem extends Item implements ITrinket {
	public PaperBagItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals("head") && slot.equals("mask");
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
		String translatedTooltip = new TranslatableText("tooltip.spookytime.paper_bag").asString();
		String[] translatedTooltipWords = translatedTooltip.split(" ");
		
		StringBuilder tooltipBuilder = new StringBuilder();
		for (int i = 1; i <= translatedTooltipWords.length; i++) {
			tooltipBuilder.append(translatedTooltipWords[i - 1]);
			
			if (i % 4 == 0) {
				list.add(new LiteralText(tooltipBuilder.toString()).formatted(Formatting.GRAY));
				tooltipBuilder = new StringBuilder();
			} else {
				tooltipBuilder.append(" ");
			}
		}
		
		super.appendTooltip(itemStack, world, list, tooltipContext);
	}
}
