package com.fabriccommunity.thehallow.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import com.fabriccommunity.thehallow.entity.CrowEntity;

public class CrowEntityModel extends EntityModel<CrowEntity> implements ModelWithHead {
	private final ModelPart body;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart tail;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart head;
	
	public CrowEntityModel() {
		textureWidth = 32;
		textureHeight = 16;
		
		body = new ModelPart(this, 0, 0);
		body.setPivot(0.0F, 22.0F, 0.0F);
		setRotationAngle(body, 0.6109F, 0.0F, 0.0F);
		body.addCuboid(-1.5F, -5.5F, -1.0F, 3, 6, 3, 0.0F, false);
		
		leftLeg = new ModelPart(this);
		leftLeg.setPivot(1.0F, 0.0F, 1.0F);
		setRotationAngle(leftLeg, -0.6109F, 0.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.addCuboid("left_leg", -0.75F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 0, 0);
		leftLeg.addCuboid("left_foot", -0.75F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 6, 9);
		
		rightLeg = new ModelPart(this);
		rightLeg.setPivot(-1.0F, 0.0F, 1.0F);
		setRotationAngle(rightLeg, -0.6109F, 0.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.addCuboid("right_leg", -0.25F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 10, 0);
		rightLeg.addCuboid("right_foot", -0.25F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 6, 10);
		
		tail = new ModelPart(this, 8, 9);
		tail.setPivot(0.0F, 0.0F, 1.5F);
		setRotationAngle(tail, -0.8727F, 0.0F, 0.0F);
		body.addChild(tail);
		tail.addCuboid(-1.5F, -0.5F, -0.5F, 3, 1, 3, 0.0F, false);
		
		leftWing = new ModelPart(this, 12, 0);
		leftWing.setPivot(1.5F, -5.0F, 0.5F);
		setRotationAngle(leftWing, 0.3491F, 0.0F, 0.0F);
		body.addChild(leftWing);
		leftWing.addCuboid(-0.25F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);
		
		rightWing = new ModelPart(this, 20, 0);
		rightWing.setPivot(-1.5F, -5.0F, 0.5F);
		setRotationAngle(rightWing, 0.3491F, 0.0F, 0.0F);
		body.addChild(rightWing);
		rightWing.addCuboid(-0.75F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);
		
		head = new ModelPart(this);
		head.setPivot(0.0F, -5.5F, 1.0F);
		setRotationAngle(head, -0.4363F, 0.0F, 0.0F);
		body.addChild(head);
		head.addCuboid("head", -1.0F, -2.0F, -1.5F, 2, 3, 2, 0.0F, 0, 9);
		head.addCuboid("beak", -0.5F, -1.0F, -3.0F, 1, 1, 2, 0.0F, 17, 0);
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, float r, float g, float b, float f) {
		body.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
	}
	
	public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
		ModelPart.pitch = x;
		ModelPart.yaw = y;
		ModelPart.roll = z;
	}
	
	@Override
	public void setAngles(CrowEntity crow, float limbAngle, float limbDistance, float age, float headYaw, float headPitch) {
		
		head.pitch = headPitch * 0.017453292F;
		head.yaw = headYaw * 0.017453292F;
		
		if (crow.isInAir()) {
			leftLeg.pitch = 0.55F;
			rightLeg.pitch = 0.55F;
			this.leftWing.roll = 0.0873F + age;
			this.rightWing.roll = -0.0873F - age;
		} else {
			leftLeg.pitch = -0.6109F + MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
			rightLeg.pitch = -0.6109F + MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
			leftWing.roll = 0F;
			rightWing.roll = 0F;
		}
	}
	
	@Override
	public ModelPart getHead() {
		return head;
	}
}
