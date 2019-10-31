package com.fabriccommunity.thehallow.client.render;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.entity.ShotgunProjectileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

public class ShotgunProjectileEntityRenderer extends EntityRenderer<ShotgunProjectileEntity> {
	private static final Identifier SKIN = new Identifier(TheHallow.MOD_ID, "textures/entity/bullet.png");

	public ShotgunProjectileEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher);
	}

	@Override
	public void render(ShotgunProjectileEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		bindEntityTexture(entity);

		// Code is copied from Techguns and re-adapted for fabric
		GlStateManager.pushMatrix();

		GlStateManager.translatef((float) x, (float) y, (float) z);
		GlStateManager.rotatef(entity.yaw - 90, 0.0f, 1.0f, 0.0f);
		GlStateManager.rotatef(entity.pitch, 0.0F, 0.0f, 1.0f);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBufferBuilder();
		float u1 = 0.0f;
		float u2 = 1.0f;
		float v1 = 0.0f;
		float v2 = 1.0f;
		float f10 = 0.025F;
		GlStateManager.enableRescaleNormal();

		GlStateManager.rotatef(45.0f, 1.0f, 0.0f, 0.0f);
		GlStateManager.scalef(f10, f10, f10);

		double length = 8.0d; //Actually, this is half of length/width
		double width = 8.0d;

		for (int i = 0; i < 4; ++i) {
			GlStateManager.rotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GlStateManager.normal3f(0.0f, 0.0f, f10);


			bufferBuilder.begin(7, VertexFormats.POSITION_UV);

			bufferBuilder.vertex(-length, -width, 0.0d).texture(u1, v1).next();
			bufferBuilder.vertex(length, -width, 0.0d).texture(u2, v1).next();
			bufferBuilder.vertex(length, width, 0.0d).texture(u2, v2).next();
			bufferBuilder.vertex(-length, width, 0.0d).texture(u1, v2).next();

			tessellator.draw();
		}

		GlStateManager.disableRescaleNormal();

		GlStateManager.popMatrix();
	}

	@Override
	protected Identifier getTexture(ShotgunProjectileEntity projectile) {
		return SKIN;
	}
}
