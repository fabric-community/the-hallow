package com.fabriccommunity.spookytime.block;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

public class SpookySignBlock extends SignBlock implements SpookySign {
	private final Identifier texture;
	
	public SpookySignBlock(Identifier texture, Settings settings) {
		super(settings);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
