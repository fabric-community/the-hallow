package com.fabriccommunity.spookytime.doomtree.model;

import java.util.function.Function;

import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;

public class DoomTreeHeart extends DoomLogTerminal {
	
	protected DoomTreeHeart(Sprite sprite, Function<Identifier, Sprite> spriteMap) {
		super(sprite, spriteMap);
	}
	
	@Override
	protected int getHeight(BlockState state, int y) {
		return 0;
	}

	@Override
	protected int[] makeGlowColors() {
		return null;
	}
	
	@Override
	protected int glowColor(int height) {
		return 0xFF000000 | CHANNEL_HIGH_COLOR;
	}
	
	public static DoomTreeHeart create(Function<Identifier, Sprite> spriteMap) {
		return new DoomTreeHeart(spriteMap.apply(TERMINAL_TEXTURES.get(0)), spriteMap);
	}
}
