package com.fabriccommunity.spookytime.doomtree.model;

import java.util.List;
import java.util.function.Function;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.client.model.SpookyModels;
import com.google.common.collect.ImmutableList;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class DoomLogChannel extends DoomLog {
	
	public static final List<Identifier> CHANNEL_TEXTURES = ImmutableList.of(
			SpookyTime.id("block/doom_log_channel_0_0"),
			SpookyTime.id("block/doom_log_channel_0_1"),
			SpookyTime.id("block/doom_log_channel_0_2"),
			SpookyTime.id("block/doom_log_channel_0_3"));
	
	protected final Sprite[] channelSprite = new Sprite[4];
    
	protected DoomLogChannel(Sprite sprite, Function<Identifier, Sprite> spriteMap) {
		super(sprite, spriteMap);
		for (int i = 0; i < 4; i++) {
			channelSprite[i] = spriteMap.apply(CHANNEL_TEXTURES.get(i));
		}
	}

	@Override
	protected int[] makeGlowColors() {
		return makeGlowColors(CHANNEL_LOW_COLOR, CHANNEL_HIGH_COLOR);
	}

	@Override
	protected void emitSideFace(QuadEmitter qe, Direction face, int bits, int height) {
		final int logTexture = (bits >> 2) & 3;
		qe.material(outerMaterial)
		.square(face, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, channelSprite[logTexture], MutableQuadView.BAKE_LOCK_UV);
		SpookyModels.contractUVs(0, channelSprite[logTexture], qe);
		qe.emit();
	}
	
	public static DoomLogChannel create(Function<Identifier, Sprite> spriteMap) {
		return new DoomLogChannel(spriteMap.apply(CHANNEL_TEXTURES.get(0)), spriteMap);
	}
}
