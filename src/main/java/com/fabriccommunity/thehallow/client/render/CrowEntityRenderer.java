package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.client.model.CrowEntityModel;
import com.fabriccommunity.thehallow.entity.CrowEntity;

public class CrowEntityRenderer extends MobEntityRenderer<CrowEntity, CrowEntityModel> {
	private static final Identifier SKIN = new Identifier(TheHallow.MOD_ID, "textures/entity/crow.png");
	
	public CrowEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new CrowEntityModel(), 0.3F);
	}
	
	@Override
	public Identifier getTexture(CrowEntity crow) {
		return SKIN;
	}
}
