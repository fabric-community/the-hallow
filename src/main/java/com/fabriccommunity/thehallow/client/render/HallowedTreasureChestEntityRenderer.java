package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;

public class HallowedTreasureChestEntityRenderer extends EntityRenderer<HallowedTreasureChestEntity> {
	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private static final Quaternion INITIAL_ROTATION = new Quaternion(180, 1, 0, 0);
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	public HallowedTreasureChestEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(HallowedTreasureChestEntity chest, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		matrixStack.push();
		
		// initial size and position
		matrixStack.translate(0.275, 0.57, 0.275);
		matrixStack.multiply(INITIAL_ROTATION);
		matrixStack.scale(0.57f, 0.57f, 0.57f);
		
		// calculate interpolated render rotation from last rotation
		float interpolated = chest.previousRotation + (chest.rotation - chest.previousRotation) * tickDelta;
		
		matrixStack.translate(0.5, 0.5, 0.5);
		matrixStack.multiply(new Quaternion(interpolated, 0f, 1f, 0f));
		matrixStack.translate(-0.5, -0.5, -0.5);
		
		// jiggle after finishing spin
		if (chest.getEndProgress() != 0) {
			matrixStack.translate(0.5, 0.5, 0.5);
			matrixStack.multiply(new Quaternion((float) Math.sin(chest.getEndProgress()), 0f, 0f, 1f));
			matrixStack.translate(-0.5f, -0.5f, -0.5f);
		}
		
		
		// render chest
		//renderManager.textureManager.bindTexture(TEXTURE);
		updateHingeProgress(chest, chestModel);
		chestModel.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
		
		
		// finish
		matrixStack.pop();
	}
	
	private void updateHingeProgress(HallowedTreasureChestEntity chest, TreasureChestModel chestModel) {
		// 6.28 (closed) -> 5 (open)
		float percentDistance = (chest.getHingeProgress() / (float) HallowedTreasureChestEntity.MAX_HINGE_PROGRESS) * 1.35f;
		chestModel.getLid().pitch = 6.28f - percentDistance;
	}
	
	@Override
	public Identifier getTexture(HallowedTreasureChestEntity spookyTreasureChestEntity) {
		return TEXTURE;
	}
}
