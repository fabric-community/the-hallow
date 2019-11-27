package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import com.mojang.blaze3d.platform.GlStateManager;

import com.fabriccommunity.thehallow.TheHallow;

public class MummyEntityRenderer extends ZombieEntityRenderer {
	private static final Identifier SKIN = TheHallow.id("textures/entity/mummy.png");
	
	public MummyEntityRenderer(EntityRenderDispatcher erd) {
		super(erd);
	}
	
	@Override
	protected void scale(ZombieEntity entity, MatrixStack matrixStack, float f1) {
		GlStateManager.scalef(1.2f, 1.2f, 1.2f);
		super.scale(entity, matrixStack, f1);
	}
	
	@Override
	public Identifier getTexture(ZombieEntity entity) {
		return SKIN;
	}
}
