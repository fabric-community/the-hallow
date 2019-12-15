package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;

public class HallowedTreasureChestEntityRenderer extends EntityRenderer<HallowedTreasureChestEntity> {
	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private static final Quaternion INITIAL_ROTATION_X = Vector3f.POSITIVE_X.getDegreesQuaternion(180f);
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	public HallowedTreasureChestEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(HallowedTreasureChestEntity chest, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		matrixStack.push();
		
		// initial size and position
		matrixStack.translate(-0.275, 0.57, 0.275);
		matrixStack.multiply(INITIAL_ROTATION_X);
		matrixStack.scale(0.57f, 0.57f, 0.57f);
		
		// calculate interpolated render rotation from last rotation
		float interpolated = chest.previousRotation + (chest.rotation - chest.previousRotation) * tickDelta;
		
		matrixStack.translate(0.5, 0.5, 0.5);
		matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(interpolated));
		matrixStack.translate(-0.5, -0.5, -0.5);
		
		// jiggle after finishing spin
		if (chest.getEndProgress() != 0) {
			matrixStack.translate(0.5, 0.5, 0.5);
			matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) MathHelper.sin(chest.getEndProgress())));
			matrixStack.translate(-0.5f, -0.5f, -0.5f);
		}
		
		
		// render chest
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
