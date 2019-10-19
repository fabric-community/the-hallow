package com.fabriccommunity.thehallow.block;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

public class HallowedSignBlock extends SignBlock implements HallowedSign {
	private final Identifier texture;
	
	public HallowedSignBlock(Identifier texture, Settings settings) {
		super(settings);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
