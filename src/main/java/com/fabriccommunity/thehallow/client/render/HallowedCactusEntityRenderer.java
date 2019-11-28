package com.fabriccommunity.thehallow.client.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.entity.RestlessCactusEntity;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;

public class HallowedCactusEntityRenderer extends MobEntityRenderer<RestlessCactusEntity, EntityModel<RestlessCactusEntity>> {
	private static final Identifier SKIN = new Identifier(TheHallow.MOD_ID, "textures/entity/pumpcown.png");
	
	public HallowedCactusEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, null, 0.7F);
	}
	
	@Override
	public void render(RestlessCactusEntity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		BlockRenderManager manager = MinecraftClient.getInstance().getBlockRenderManager();
		BlockState state = HallowedBlocks.RESTLESS_CACTUS.getDefaultState();
		renderManager.textureManager.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
		matrixStack.push();
		matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-yaw));
		matrixStack.translate(-0.5f, 0.0f, -0.5f);
		
		for (int i = 0; i < entity.getCactusHeight(); i++) {
			manager.renderBlockAsEntity(state, matrixStack, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
			matrixStack.translate(0.0F, 1.0F, 0.0F);
		}
		matrixStack.pop();
	}
	
	@Override
	public Identifier getTexture(RestlessCactusEntity cactus) {
		return SKIN;
	}
}
