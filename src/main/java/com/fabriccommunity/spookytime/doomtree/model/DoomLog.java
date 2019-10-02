package com.fabriccommunity.spookytime.doomtree.model;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.client.model.SimpleModel;
import com.fabriccommunity.spookytime.client.model.SpookyModels;
import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;
import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.HashCommon;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ExtendedBlockView;

public class DoomLog extends SimpleModel {

	public static final List<Identifier> TEXTURES = ImmutableList.of(
			SpookyTime.id("block/doom_log_0_0"),
			SpookyTime.id("block/doom_log_0_1"),
			SpookyTime.id("block/doom_log_0_2"),
			SpookyTime.id("block/doom_log_0_3"),
			SpookyTime.id("block/doom_log_1_0"),
			SpookyTime.id("block/doom_log_1_1"),
			SpookyTime.id("block/doom_log_1_2"),
			SpookyTime.id("block/doom_log_1_3"));

	protected final Sprite innerSide;
	protected final Sprite innerTop;
	protected final Sprite[] outerSprite = new Sprite[TEXTURES.size()];
	protected final Renderer renderer = RendererAccess.INSTANCE.getRenderer();
	protected final RenderMaterial innerMaterial = renderer.materialFinder().emissive(0, true).disableAo(0, true).disableDiffuse(0, true).find();
	protected final RenderMaterial outerMaterial = renderer.materialFinder().blendMode(0, BlockRenderLayer.TRANSLUCENT).find();

	protected DoomLog(Sprite sprite, Function<Identifier, Sprite> spriteMap) {
		super(sprite, ModelHelper.MODEL_TRANSFORM_BLOCK);
		innerSide = spriteMap.apply(new Identifier("minecraft:block/water_flow"));
		innerTop = spriteMap.apply(new Identifier("minecraft:block/water_still"));
		for (int i = 0; i < outerSprite.length; i++) {
			outerSprite[i] = spriteMap.apply(TEXTURES.get(i));
		}
	}

	@Override
	public final void emitBlockQuads(ExtendedBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		final QuadEmitter qe = context.getEmitter();
		final long bits = HashCommon.mix(pos.asLong());
		final int height = getHeight(state, pos.getY());
		emitQuads(qe, bits, height);
	}

	protected int getHeight(BlockState state, int y) {
		final Block block = state.getBlock();
		return (block instanceof DoomLogBlock) && !((DoomLogBlock) block).isPlaced 
				? getHeightFromState(state) : glowHeightFromY(y);
	}
	
	protected static int glowHeightFromY(int y) {
		return DoomLogBlock.MAX_HEIGHT - Math.abs((y % (DoomLogBlock.MAX_HEIGHT * 2)) - DoomLogBlock.MAX_HEIGHT);
	}
	
	protected int getHeightFromState(BlockState state) {
		return state.get(DoomLogBlock.HEIGHT);
	}

	@Override
	protected Mesh createMesh() {
		MeshBuilder mb = renderer.meshBuilder();
		emitQuads(mb.getEmitter(), 0, 0);
		return mb.build();
	}

	protected final void emitQuads(QuadEmitter qe, long bits, int height) {
		emitGlowFace(qe, Direction.UP, (int) bits, height, true);
		emitFace(qe, Direction.UP, (int) bits, height);
		
		bits >>= 8;
		emitGlowFace(qe, Direction.DOWN, (int) bits, height, true);
		emitFace(qe, Direction.DOWN, (int) bits, height);
		
		bits >>= 8;
		emitGlowFace(qe, Direction.EAST, (int) bits, height, false);
		emitSideFace(qe, Direction.EAST, (int) bits, height);
		
		bits >>= 8;
		emitGlowFace(qe, Direction.WEST, (int) bits, height, false);
		emitSideFace(qe, Direction.WEST, (int) bits, height);
		
		bits >>= 8;
		emitGlowFace(qe, Direction.NORTH, (int) bits, height, false);
		emitSideFace(qe, Direction.NORTH, (int) bits, height);
		
		bits >>= 8;
		emitGlowFace(qe, Direction.SOUTH, (int) bits, height, false);
		emitSideFace(qe, Direction.SOUTH, (int) bits, height);
	}

	protected final void emitGlowFace(QuadEmitter qe, Direction face, int bits, int height, boolean isY) {
		qe.material(innerMaterial)
		.square(face, 0, 0, 1, 1, 0)
		.spriteBake(0, isY ? innerTop : innerSide, MutableQuadView.BAKE_LOCK_UV + MutableQuadView.BAKE_FLIP_V);
		glow(qe, height);
		SpookyModels.contractUVs(0, innerSide, qe);
		qe.emit();
	}
	
	protected void emitFace(QuadEmitter qe, Direction face, int bits, int height) {
		final int logTexture = bits & 7;
		final int logRotation = (bits >> 3) & 3;
		qe.material(outerMaterial)
		.square(face, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, outerSprite[logTexture], MutableQuadView.BAKE_LOCK_UV + logRotation);
		SpookyModels.contractUVs(0, outerSprite[logTexture], qe);
		qe.emit();
	}

	protected void emitSideFace(QuadEmitter qe, Direction face, int bits, int height) {
		emitFace(qe, face, bits, height);
	}
	
	public static DoomLog create(Function<Identifier, Sprite> spriteMap) {
		return new DoomLog(spriteMap.apply(TEXTURES.get(0)), spriteMap);
	}

	protected void glow(QuadEmitter qe, int height) {
		for (int i = 0; i < 4; i++) {
			final float y = qe.y(i);
			qe.spriteColor(i, 0, y > 0.0001f ? glowColor(height + 1) : glowColor(height));
		}
	}

	protected static final int LOG_LOW_COLOR = 0xFFFF60;
	protected static final int LOG_HIGH_COLOR = 0xFF0000;
	protected static final int CHANNEL_LOW_COLOR = 0x03ffc2;
	protected static final int CHANNEL_HIGH_COLOR = 0x8400ff;

	protected int[] makeGlowColors() {
		return makeGlowColors(LOG_LOW_COLOR, LOG_HIGH_COLOR);
	}

	protected final int[] glowColors = makeGlowColors();

	protected int[] makeGlowColors(int lowColor, int highColor) {
		int[] result = new int[COLOR_COUNT];

		for(int i = 0; i < COLOR_COUNT; i++) {
			result[i] = interpolateColor(i, lowColor, highColor);
		}
		return result;
	}

	protected static final int COLOR_COUNT = DoomLogBlock.MAX_HEIGHT + 1;

	protected static int interpolateColor(int height, int lowColor, int highColor) {
		final float r0 = (lowColor >> 16) & 0xFF;
		final float g0 = (lowColor >> 8) & 0xFF;
		final float b0 = lowColor & 0xFF;

		final float r1 = (highColor >> 16) & 0xFF;
		final float g1 = (highColor >> 8) & 0xFF;
		final float b1 = highColor & 0xFF;

		final float w = height / (float) COLOR_COUNT;
		final int r = Math.round(MathHelper.lerp(w, r0, r1));
		final int g = Math.round(MathHelper.lerp(w, g0, g1));
		final int b = Math.round(MathHelper.lerp(w, b0, b1));

		return 0xFF000000 | (r << 16) | (g << 8) | b;
	}

	protected int glowColor(int height) {
		return glowColors[MathHelper.clamp(height, 0, DoomLogBlock.MAX_HEIGHT)];
	}

}
