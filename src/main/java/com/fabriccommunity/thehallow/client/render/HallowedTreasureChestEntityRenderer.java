package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import com.mojang.blaze3d.platform.GlStateManager;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;

public class HallowedTreasureChestEntityRenderer extends EntityRenderer<HallowedTreasureChestEntity> {
	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	public HallowedTreasureChestEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(HallowedTreasureChestEntity chest, double x, double y, double z, float partialTicks, float float_2) {
		GlStateManager.pushMatrix();
		
		// initial size and position
		GlStateManager.translatef((float) x - .275f, (float) y + .57f, (float) z + .275f);
		GlStateManager.rotatef(180, 1, 0, 0);
		GlStateManager.scalef(.57f, .57f, .57f);
		
		// calculate interpolated render rotation from last rotation
		double interpolated = chest.previousRotation + (chest.rotation - chest.previousRotation) * partialTicks;
		
		GlStateManager.translatef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotated(interpolated, 0, 1, 0);
		GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
		
		// jiggle after finishing spin
		if (chest.getEndProgress() != 0) {
			GlStateManager.translatef(0.5F, 0.5F, 0.5F);
			GlStateManager.rotatef((float) Math.sin(chest.getEndProgress()), 0, 0, 1);
			GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
		}
		
		
		// render chest
		bindTexture(TEXTURE);
		updateHingeProgress(chest, chestModel);
		chestModel.render();
		
		
		// finish
		GlStateManager.popMatrix();
	}
	
	private void updateHingeProgress(HallowedTreasureChestEntity chest, TreasureChestModel chestModel) {
		// 6.28 (closed) -> 5 (open)
		float percentDistance = (chest.getHingeProgress() / (float) HallowedTreasureChestEntity.MAX_HINGE_PROGRESS) * 1.35f;
		chestModel.getLid().pitch = 6.28f - percentDistance;
	}
	
	@Override
	protected Identifier getTexture(HallowedTreasureChestEntity spookyTreasureChestEntity) {
		return null;
	}
}
