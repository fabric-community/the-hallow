package com.fabriccommunity.thehallow.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;

public class HallowWallSignBlock extends WallSignBlock implements HallowSign {
	private final Identifier texture;
	
	public HallowWallSignBlock(Identifier texture, Settings settings) {
		super(settings);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
