package com.fabriccommunity.thehallow.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class TreasureChestModel extends Model {
	private ModelPart lid = (new ModelPart(this, 0, 0)).setTextureSize(64, 64);
	private ModelPart base;
	private ModelPart hatch;
	
	public TreasureChestModel() {
		super(RenderLayer::getEntityCutoutNoCull);
		this.lid.addCuboid(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
		this.lid.pivotX = 1.0F;
		this.lid.pivotY = 7.0F;
		this.lid.pivotZ = 15.0F;
		
		this.hatch = (new ModelPart(this, 0, 0)).setTextureSize(64, 64);
		this.hatch.addCuboid(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
		this.hatch.pivotX = 8.0F;
		this.hatch.pivotY = 7.0F;
		this.hatch.pivotZ = 15.0F;
		
		this.base = (new ModelPart(this, 0, 19)).setTextureSize(64, 64);
		this.base.addCuboid(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
		this.base.pivotX = 1.0F;
		this.base.pivotY = 6.0F;
		this.base.pivotZ = 1.0F;
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, float r, float g, float b, float f) {
		this.hatch.pitch = this.lid.pitch;
		this.lid.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		this.hatch.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		this.base.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
	}
	
	public ModelPart getLid() {
		return this.lid;
	}
}
