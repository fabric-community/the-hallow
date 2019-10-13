package com.fabriccommunity.spookytime.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;

public class SpookyWallSignBlock extends WallSignBlock implements SpookySign {
	private final Identifier texture;
	
	public SpookyWallSignBlock(Identifier texture, Settings settings) {
		super(settings);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
