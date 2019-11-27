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
	
	private static final Quaternion FIRST_ROTATION = new Quaternion(42f, 0f, 1f, 0f);
	
	public PumpcownStemFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> rendererContext) {
		super(rendererContext);
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, PumpcownEntity pumpcown, float f, float g, float tickDelta, float h, float j, float k) {
		if (!pumpcown.isBaby() && !pumpcown.isInvisible()) {
			BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
	         BlockState blockState = PumpcownEntity.STEM_FEATURE;
	         int m = LivingEntityRenderer.method_23622(pumpcown, 0.0F);
	         matrixStack.push();
	         matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
	         matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
	         matrixStack.scale(-1.0F, -1.0F, 1.0F);
	         matrixStack.translate(-0.5D, -0.5D, -0.5D);
	         blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
	         matrixStack.pop();
	         matrixStack.push();
	         matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
	         matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
	         matrixStack.translate(0.10000000149011612D, 0.0D, -0.6000000238418579D);
	         matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
	         matrixStack.scale(-1.0F, -1.0F, 1.0F);
	         matrixStack.translate(-0.5D, -0.5D, -0.5D);
	         blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
	         matrixStack.pop();
	         matrixStack.push();
	         ((CowEntityModel)this.getModel()).getHead().rotate(matrixStack);
	         matrixStack.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
	         matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
	         matrixStack.scale(-1.0F, -1.0F, 1.0F);
	         matrixStack.translate(-0.5D, -0.5D, -0.5D);
	         blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, m);
	         matrixStack.pop();
		}
	}
}
