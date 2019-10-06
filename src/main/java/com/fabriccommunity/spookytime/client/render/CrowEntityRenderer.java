package com.fabriccommunity.spookytime.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.client.model.CrowEntityModel;
import com.fabriccommunity.spookytime.entity.CrowEntity;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CrowEntityRenderer extends MobEntityRenderer<CrowEntity, CrowEntityModel> {
	private static final Identifier SKIN = new Identifier(SpookyTime.MOD_ID, "textures/entity/crow.png");
	
	public CrowEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new CrowEntityModel(), 0.3F);
	}
	
	@Override
	protected Identifier getTexture(CrowEntity crow) {
		return SKIN;
	}
}
