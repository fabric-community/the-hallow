package com.fabriccommunity.thehallow.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class HallowedWallSignBlock extends WallSignBlock implements HallowedSign {
	private final Identifier texture;
	
	public HallowedWallSignBlock(Identifier texture, SignType type, Settings settings) {
		super(settings, type);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
