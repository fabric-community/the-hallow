package com.fabriccommunity.thehallow.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.entity.PumpcownEntity;

@Environment(EnvType.CLIENT)
public class PumpcownStemFeatureRenderer<T extends PumpcownEntity> extends FeatureRenderer<T, CowEntityModel<T>> {
	
	private static final Quaternion MINUS_48_YAW = Vector3f.POSITIVE_Y.getDegreesQuaternion(-48f);
	private static final Quaternion PLUS_48_YAW = Vector3f.POSITIVE_Y.getDegreesQuaternion(42f);
	private static final Quaternion MINUS_78_YAW = Vector3f.POSITIVE_Y.getDegreesQuaternion(78f);
	
	public PumpcownStemFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> rendererContext) {
		super(rendererContext);
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, PumpcownEntity pumpcown, float f, float g, float tickDelta, float h, float j, float k) {
		if (!pumpcown.isBaby() && !pumpcown.isInvisible()) {
			BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
			BlockState blockState = PumpcownEntity.STEM_FEATURE;
			int m = LivingEntityRenderer.getOverlay(pumpcown, 0.0F);
			matrixStack.push();
			matrixStack.translate(0.2, -0.35, 0.5);
			matrixStack.multiply(MINUS_48_YAW);
			matrixStack.scale(-1.0F, -1.0F, 1.0F);
			matrixStack.translate(-0.5, -0.5, -0.5);
			blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
			matrixStack.pop();
			matrixStack.push();
			matrixStack.translate(0.2, -0.35, 0.5);
			matrixStack.multiply(PLUS_48_YAW);
			matrixStack.translate(0.1, 0, -0.6);
			matrixStack.multiply(MINUS_48_YAW);
			matrixStack.scale(-1.0F, -1.0F, 1.0F);
			matrixStack.translate(-0.5, -0.5, -0.5);
			blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
			matrixStack.pop();
			matrixStack.push();
			((CowEntityModel)this.getContextModel()).getHead().rotate(matrixStack);
			matrixStack.translate(0, -0.7, -0.2);
			matrixStack.multiply(MINUS_78_YAW);
			matrixStack.scale(-1.0F, -1.0F, 1.0F);
			matrixStack.translate(-0.5, -0.5, -0.5);
			blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
			matrixStack.pop();
		}
	}
}
