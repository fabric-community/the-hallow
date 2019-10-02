package com.fabriccommunity.spookytime.common.items;

import dev.emi.trinkets.api.ITrinket;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.ArrayList;
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
        String tooltip = new TranslatableText("spookytime.paper_bag_tooltip").asString();
        String[] splitTooltip = tooltip.split(" ");
        String[] endList = new String[splitTooltip.length / 5];

        for(int i = 0; i < splitTooltip.length; i++) {
            int lineIndex = i / 5;
            endList[lineIndex] = (endList[lineIndex] == null ? "" : endList[lineIndex]) + (i % 5 == 0 ? "" : " ") + splitTooltip[i];
        }

        for(String line : endList) {
            list.add(new LiteralText(line).formatted(Formatting.GRAY));
        }

        super.appendTooltip(itemStack, world, list, tooltipContext);
    }
}
