package com.fabriccommunity.thehallow.block;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class HallowedButtonBlock extends AbstractButtonBlock {
	protected final boolean wooden;
	
	public HallowedButtonBlock(boolean wooden, Settings settings) {
		super(wooden, settings);
		this.wooden = wooden;
	}
	
	@Override
	protected SoundEvent getClickSound(boolean on) {
		if (wooden) return on ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
		else return on ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
	}
}
