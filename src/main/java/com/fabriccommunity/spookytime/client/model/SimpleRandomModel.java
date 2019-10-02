package com.fabriccommunity.spookytime.client.model;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import com.fabriccommunity.spookytime.SpookyTime;
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
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ExtendedBlockView;

public class SimpleRandomModel extends SimpleModel {
	protected final Sprite[] sprites;
	protected final Renderer renderer = RendererAccess.INSTANCE.getRenderer();
	protected final RenderMaterial material = renderer.materialFinder().find();
	protected final int maxTextureIndex;
	
	public SimpleRandomModel(Function<Identifier, Sprite> spriteMap, List<Identifier> textures) {
		super(spriteMap.apply(textures.get(0)), ModelHelper.MODEL_TRANSFORM_BLOCK);
		final int textureCount = textures.size();
		maxTextureIndex = textureCount - 1;
		sprites = new Sprite[textureCount];
		for (int i = 0; i < textureCount; i++) {
			sprites[i] = spriteMap.apply(textures.get(i));
		}
	}

	@Override
	public final void emitBlockQuads(ExtendedBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		final QuadEmitter qe = context.getEmitter();
		final long bits = HashCommon.mix(pos.asLong());
		emitQuads(qe, bits);
	}

	@Override
	protected Mesh createMesh() {
		MeshBuilder mb = renderer.meshBuilder();
		emitQuads(mb.getEmitter(), 0);
		return mb.build();
	}

	protected final void emitQuads(QuadEmitter qe, long bits) {
		emitFace(qe, Direction.UP, (int) bits);
		
		bits >>= 8;
		emitFace(qe, Direction.DOWN, (int) bits);
		
		bits >>= 8;
		emitFace(qe, Direction.EAST, (int) bits);
		
		bits >>= 8;
		emitFace(qe, Direction.WEST, (int) bits);
		
		bits >>= 8;
		emitFace(qe, Direction.NORTH, (int) bits);
		
		bits >>= 8;
		emitFace(qe, Direction.SOUTH, (int) bits);
	}
	
	protected void emitFace(QuadEmitter qe, Direction face, int bits) {
		final int texture = Math.min(maxTextureIndex, bits & 3);
		final int rotation = (bits >> 2) & 3;
		
		qe.material(material)
		.square(face, 0, 0, 1, 1, 0)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, sprites[texture], MutableQuadView.BAKE_LOCK_UV + rotation);
		SpookyModels.contractUVs(0, sprites[texture], qe);
		qe.emit();
	}
	
	public static void register(String id, String... textures) {
		final ImmutableList.Builder<Identifier> builder = ImmutableList.builder();
		
		for (String tex : textures) {
			builder.add(SpookyTime.id(tex));
		}
		
		final List<Identifier> list = builder.build();
		
		SpookyModels.register(id, new SimpleUnbakedModel(spriteMap -> new SimpleRandomModel(spriteMap, list), list));
	}
}
