package com.fabriccommunity.spookytime.client.render;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PumpcownEntityRenderer extends MobEntityRenderer<PumpcownEntity, CowEntityModel<PumpcownEntity>> {
	private static final Identifier SKIN = new Identifier(SpookyTime.MOD_ID, "textures/entity/pumpcown.png");
	
	public PumpcownEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new CowEntityModel(), 0.7F);
		this.addFeature(new PumpcownStemFeatureRenderer(this));
	}
	
	@Override
	protected Identifier getTexture(PumpcownEntity pumpcown) {
		return SKIN;
	}
}
