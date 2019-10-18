package com.fabriccommunity.thehallow.block;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

public class HallowSignBlock extends SignBlock implements HallowSign {
	private final Identifier texture;
	
	public HallowSignBlock(Identifier texture, Settings settings) {
		super(settings);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
