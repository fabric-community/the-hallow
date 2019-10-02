package com.fabriccommunity.spookytime.doomtree.model;

import java.util.List;
import java.util.function.Function;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.client.model.SpookyModels;
import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;
import com.google.common.collect.ImmutableList;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class DoomLogTerminal extends DoomLog {
	
	public static final List<Identifier> TERMINAL_TEXTURES = ImmutableList.of(
			SpookyTime.id("block/doom_log_terminal"));
	
	protected final Sprite termimnalSprite;
    
	protected DoomLogTerminal(Sprite sprite, Function<Identifier, Sprite> spriteMap) {
		super(sprite, spriteMap);
		termimnalSprite = spriteMap.apply(TERMINAL_TEXTURES.get(0));
	}
	
	@Override
	protected int getHeightFromState(BlockState state) {
		return DoomLogBlock.TERMINAL_HEIGHT;
	}

	@Override
	protected int[] makeGlowColors() {
		return makeGlowColors(CHANNEL_LOW_COLOR, CHANNEL_HIGH_COLOR);
	}
	
	@Override
	protected void emitSideFace(QuadEmitter qe, Direction face, int bits, int height) {
		qe.material(outerMaterial)
		.square(face, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, termimnalSprite, MutableQuadView.BAKE_LOCK_UV);
		SpookyModels.contractUVs(0, termimnalSprite, qe);
		qe.emit();
	}
	
	public static DoomLogTerminal create(Function<Identifier, Sprite> spriteMap) {
		return new DoomLogTerminal(spriteMap.apply(TERMINAL_TEXTURES.get(0)), spriteMap);
	}
}
