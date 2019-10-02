package com.fabriccommunity.spookytime.doomtree.model;

import static com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity.*;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.client.model.SimpleModel;
import com.fabriccommunity.spookytime.doomtree.AlchemicalBasinBlockEntity;
import com.google.common.collect.ImmutableList;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachedBlockView;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ExtendedBlockView;

public class AlchemicalBasin extends SimpleModel {
	public static final List<Identifier> TEXTURES = ImmutableList.of(
			SpookyTime.id("block/alchemical_basin_base"),
			SpookyTime.id("block/alchemical_basin_side_a"),
			SpookyTime.id("block/alchemical_basin_side_b"),
			SpookyTime.id("block/alchemical_basin_feet"),
			SpookyTime.id("block/alchemical_basin_frame"));

	protected final Sprite[] sprites = new Sprite[TEXTURES.size()];
	protected static final int BASE = 0;
	protected static final int SIDE_A = 1;
	protected static final int SIDE_B = 2;
	protected static final int FEET = 3;
	protected static final int FRAME = 4;

	protected static final float PX1 = 1f / 16f;
	protected static final float PX2 = 2f / 16f;
	protected static final float PX3 = 3f / 16f;
	protected static final float PX4 = 4f / 16f;
	protected static final float PX12 = 12f / 16f;
	protected static final float PX13 = 13f / 16f;
	protected static final float PX14 = 14f / 16f;
	protected static final float PX15 = 15f / 16f;
	
	protected static final float FLUID_BOTTOM_DEPTH = 1f - PX3;
	/** negative, because added to depth, making fluid stop surface less deep */
	protected static final float FLUID_DEPTH_SPAN = PX1 - FLUID_BOTTOM_DEPTH;
	protected static final float LEVEL_MULTIPLIER = FLUID_DEPTH_SPAN / AlchemicalBasinBlockEntity.MAX_LEVEL;
			
	public static final int WITCH_WATER_COLOR = 0xFF5900A3;
	public static final int WATER_COLOR = 0xFF188DFF;
	
	protected final Renderer renderer = RendererAccess.INSTANCE.getRenderer();
	protected final RenderMaterial matCutout = renderer.materialFinder().blendMode(0, BlockRenderLayer.CUTOUT).find();
	protected final RenderMaterial matSolid = renderer.materialFinder().blendMode(0, BlockRenderLayer.SOLID).find();
	protected final RenderMaterial matTranslucent = renderer.materialFinder().blendMode(0, BlockRenderLayer.TRANSLUCENT).find();

	protected final int sideIndexA;
	protected final int sideIndexB;
	
	protected final Sprite waterSprite;
	
	protected AlchemicalBasin(Sprite sprite, Function<Identifier, Sprite> spriteMap, boolean isFrame) {
		super(sprite, ModelHelper.MODEL_TRANSFORM_BLOCK);
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = spriteMap.apply(TEXTURES.get(i));
		}
		sideIndexA = isFrame ? FRAME : SIDE_A;
		sideIndexB = isFrame ? FRAME : SIDE_B;
		waterSprite = spriteMap.apply(new Identifier("minecraft:block/water_still"));
	}

	@Override
	public final void emitBlockQuads(ExtendedBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		final QuadEmitter qe = context.getEmitter();
		emitContents(qe, ((RenderAttachedBlockView) blockView).getBlockEntityRenderAttachment(pos));
		emitQuads(qe);
	}

	private void emitContents(QuadEmitter qe, Object renderData) {
		if (renderData == null || !(renderData instanceof AlchemicalBasinBlockEntity)) return;
		
		AlchemicalBasinBlockEntity myBe = (AlchemicalBasinBlockEntity) renderData;
		
		final int mode = myBe.mode();
		
		if (mode == MODE_PRIMED_WITCHWATER) {
			renderFluidContent(qe, WITCH_WATER_COLOR, myBe.level());
		} else if (mode == MODE_PRIMED_WATER) {
			renderFluidContent(qe, WATER_COLOR, myBe.level());
		}
	}

	private void renderFluidContent(QuadEmitter qe, int color, int level) {
		final float depth = FLUID_BOTTOM_DEPTH + LEVEL_MULTIPLIER * level;
		final float height = Math.min(PX13, 1f - depth);
		
		qe.material(matTranslucent)
		.square(Direction.UP, PX1, PX1, PX15, PX15, depth)
		.spriteColor(0, color, color, color, color)
		.spriteBake(0, waterSprite, MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		qe.material(matSolid)
		.square(Direction.EAST, PX2, PX4, PX14, height , PX1)
		.spriteColor(0, color, color, color, color)
		.spriteBake(0, waterSprite, MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matSolid)
		.square(Direction.WEST, PX2, PX4, PX14, height, PX1)
		.spriteColor(0, color, color, color, color)
		.spriteBake(0, waterSprite, MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matSolid)
		.square(Direction.NORTH, PX2, PX4, PX14, height, PX1)
		.spriteColor(0, color, color, color, color)
		.spriteBake(0, waterSprite, MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matSolid)
		.square(Direction.SOUTH, PX2, PX4, PX14, height, PX1)
		.spriteColor(0, color, color, color, color)
		.spriteBake(0, waterSprite, MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
	}

	@Override
	protected Mesh createMesh() {
		MeshBuilder mb = renderer.meshBuilder();
		emitQuads(mb.getEmitter());
		return mb.build();
	}

	protected final void emitQuads(QuadEmitter qe) {
		qe.material(matSolid)
		.square(Direction.DOWN, 0, 0, 1, 1, PX2)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		qe.material(matCutout)
		.square(Direction.DOWN, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[FEET], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matSolid)
		.square(Direction.UP, 0, 0, 1, 1, PX13)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matCutout)
		.square(Direction.UP, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		// OUTER SIDES
		
		qe.material(matTranslucent)
		.square(Direction.EAST, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.WEST, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.NORTH, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.SOUTH, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		// INNER SIDES TOP
		qe.material(matTranslucent)
		.square(Direction.EAST, PX1, PX3, PX15, 1, PX15)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.WEST, PX1, PX3, PX15, 1, PX15)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.NORTH, PX1, PX3, PX15, 1, PX15)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.SOUTH, PX1, PX3, PX15, 1, PX15)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		// INNER SIDES BOTTOM
		qe.material(matTranslucent)
		.square(Direction.EAST, 0, 0, 1, PX2, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.WEST, 0, 0, 1, PX2, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexA], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.NORTH, 0, 0, 1, PX2, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matTranslucent)
		.square(Direction.SOUTH, 0, 0, 1, PX2, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[sideIndexB], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		// FRAME OUTER EDGES
		qe.material(matCutout)
		.square(Direction.UP, 0, 0, 1, 1, PX12)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		qe.material(matCutout)
		.square(Direction.DOWN, 0, 0, 1, 1, PX13)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
		
		qe.material(matCutout)
		.square(Direction.EAST, 0, PX4, 1, PX14, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matCutout)
		.square(Direction.WEST, 0, PX4, 1, PX14, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matCutout)
		.square(Direction.NORTH, 0, PX4, 1, PX14, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();

		qe.material(matCutout)
		.square(Direction.SOUTH, 0, PX4, 1, PX14, PX14)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[BASE], MutableQuadView.BAKE_LOCK_UV);
		qe.emit();
	}


	public static AlchemicalBasin create(Function<Identifier, Sprite> spriteMap) {
		return new AlchemicalBasin(spriteMap.apply(TEXTURES.get(SIDE_A)), spriteMap, false);
	}
	
	public static AlchemicalBasin createFrame(Function<Identifier, Sprite> spriteMap) {
		return new AlchemicalBasin(spriteMap.apply(TEXTURES.get(SIDE_A)), spriteMap, true);
	}
}
