package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.TheHallow;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

public class HallowedSignBlock extends SignBlock implements HallowedSign {
	private final Identifier texture;
	
	public HallowedSignBlock(Identifier texture, Settings settings) {
		super(settings, TheHallow.HALLOWED_SIGN_TYPE);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
