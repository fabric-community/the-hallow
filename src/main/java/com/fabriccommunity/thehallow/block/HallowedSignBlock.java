package com.fabriccommunity.thehallow.block;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class HallowedSignBlock extends SignBlock implements HallowedSign {
	private final Identifier texture;
	
	public HallowedSignBlock(Identifier texture, SignType type, Settings settings) {
		super(settings, type);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
