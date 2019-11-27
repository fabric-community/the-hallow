package com.fabriccommunity.thehallow.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.entity.PumpcownEntity;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PumpcownEntityRenderer extends MobEntityRenderer<PumpcownEntity, CowEntityModel<PumpcownEntity>> {
	private static final Identifier SKIN = new Identifier(TheHallow.MOD_ID, "textures/entity/pumpcown.png");
	
	public PumpcownEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new CowEntityModel(), 0.7F);
		this.addFeature(new PumpcownStemFeatureRenderer(this));
	}
	
	@Override
	public Identifier getTexture(PumpcownEntity pumpcown) {
		return SKIN;
	}
}
