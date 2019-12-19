package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.entity.ShotgunProjectileEntity;

public class ShotgunProjectileEntityRenderer extends EntityRenderer<ShotgunProjectileEntity> {
	private static final Identifier SKIN = TheHallow.id("textures/entity/bullet.png");
	
	private static final Quaternion INITIAL_ROTATION_QUAT = new Quaternion(45f, 1f, 0f, 0f);
	private static final Quaternion SECOND_ROTATION_QUAT = new Quaternion(90f, 1f, 0f, 0f);

	public ShotgunProjectileEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher);
	}

	@Override
	public void render(ShotgunProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		renderManager.textureManager.bindTexture(SKIN);

		// Code is derived from pWn3d1337
		matrixStack.push();
		
		matrixStack.multiply(new Quaternion(yaw - 90, 0.0f, 1.0f, 0.0f));
		matrixStack.multiply(new Quaternion(entity.pitch, 0.0F, 0.0f, 1.0f));
		
		VertexConsumer buffer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(SKIN));
		float u1 = 0.0f;
		float u2 = 1.0f;
		float v1 = 0.0f;
		float v2 = 1.0f;
		float f10 = 0.025F;

		matrixStack.multiply(INITIAL_ROTATION_QUAT);
		matrixStack.scale(f10, f10, f10);

		double length = 8.0d; // Actually, this is half of length/width
		double width = 8.0d;

		for (int i = 0; i < 4; ++i) {
			matrixStack.multiply(SECOND_ROTATION_QUAT);

			buffer.vertex(-length, -width, 0.0d).color(255, 255, 255, 255).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(0f, 0f, f10).next(); //int color -- float color gets turned into it anyway
			buffer.vertex(length, -width, 0.0d).color(255, 255, 255, 255).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(0f, 0f, f10).next();
			buffer.vertex(length, width, 0.0d).color(255, 255, 255, 255).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(0f, 0f, f10).next();
			buffer.vertex(-length, width, 0.0d).color(255, 255, 255, 255).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(0f, 0f, f10).next();
		}

		matrixStack.pop();
	}

	@Override
	public Identifier getTexture(ShotgunProjectileEntity projectile) {
		return SKIN;
	}
}
