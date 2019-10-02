package com.fabriccommunity.spookytime.client.render;

import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;

@Environment(EnvType.CLIENT)
public class PumpcownStemFeatureRenderer<T extends PumpcownEntity> extends FeatureRenderer<T, CowEntityModel<T>> {
	
	public PumpcownStemFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> rendererContext) {
		super(rendererContext);
	}
	
	@Override
	public void render(PumpcownEntity pumpcown, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6, float float_7) {
		if (!pumpcown.isBaby() && !pumpcown.isInvisible()) {
			BlockState state = PumpcownEntity.STEM_FEATURE;
			this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
			GlStateManager.enableCull();
			GlStateManager.cullFace(GlStateManager.FaceSides.FRONT);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, 1.0F);
			GlStateManager.translatef(0.2F, 0.35F, 0.5F);
			GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
			BlockRenderManager manager = MinecraftClient.getInstance().getBlockRenderManager();
			GlStateManager.pushMatrix();
			GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
			manager.renderDynamic(state, 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.translatef(0.1F, 0.0F, -0.6F);
			GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
			manager.renderDynamic(state, 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			this.getModel().method_2800().applyTransform(0.0625F);
			GlStateManager.scalef(1.0F, -1.0F, 1.0F);
			GlStateManager.translatef(0.0F, 0.7F, -0.2F);
			GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
			manager.renderDynamic(state, 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.cullFace(GlStateManager.FaceSides.BACK);
			GlStateManager.disableCull();
		}
	}
	
	@Override
	public boolean hasHurtOverlay() {
		return true;
	}
}
