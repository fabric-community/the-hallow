package com.fabriccommunity.thehallow.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;

public class PaperBagItem extends Item implements ITrinket {
	public PaperBagItem(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.MASK);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		return ITrinket.equipTrinket(player, hand);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
		String translatedTooltip = new TranslatableText("text.thehallow.paper_bag").asString();
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
