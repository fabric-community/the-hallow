package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.TheHallow;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;

public class HallowedWallSignBlock extends WallSignBlock implements HallowedSign {
	private final Identifier texture;
	
	public HallowedWallSignBlock(Identifier texture, Settings settings) {
		super(settings, TheHallow.HALLOWED_WOOD_TYPE);
		this.texture = texture;
	}
	
	@Override
	public Identifier getTexture() {
		return texture;
	}
}
