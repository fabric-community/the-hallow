package com.fabriccommunity.spookytime.common.items;

import dev.emi.trinkets.api.ITrinket;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BrownBagItem extends Item implements ITrinket {

    public BrownBagItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals("head") && slot.equals("mask");
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
        list.add(new TranslatableText("spookytime.brown_bag_tooltip").formatted(Formatting.GRAY));

        super.appendTooltip(itemStack, world, list, tooltipContext);
    }
}
