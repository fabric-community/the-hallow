package com.fabriccommunity.spookytime.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.util.math.MathHelper;

import com.fabriccommunity.spookytime.entity.CrowEntity;

public class CrowEntityModel extends EntityModel<CrowEntity> implements ModelWithHead {
	private final Cuboid body;
	private final Cuboid leftLeg;
	private final Cuboid rightLeg;
	private final Cuboid tail;
	private final Cuboid leftWing;
	private final Cuboid rightWing;
	private final Cuboid head;
	
	public CrowEntityModel() {
		textureWidth = 32;
		textureHeight = 16;
		
		body = new Cuboid(this, 0, 0);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(body, 0.6109F, 0.0F, 0.0F);
		body.addBox(-1.5F, -5.5F, -1.0F, 3, 6, 3, 0.0F, false);
		
		leftLeg = new Cuboid(this);
		leftLeg.setRotationPoint(1.0F, 0.0F, 1.0F);
		setRotationAngle(leftLeg, -0.6109F, 0.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.addBox("left_leg", -0.75F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 0, 0);
		leftLeg.addBox("left_foot", -0.75F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 6, 9);
		
		rightLeg = new Cuboid(this);
		rightLeg.setRotationPoint(-1.0F, 0.0F, 1.0F);
		setRotationAngle(rightLeg, -0.6109F, 0.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.addBox("right_leg", -0.25F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 10, 0);
		rightLeg.addBox("right_foot", -0.25F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 6, 10);
		
		tail = new Cuboid(this, 8, 9);
		tail.setRotationPoint(0.0F, 0.0F, 1.5F);
		setRotationAngle(tail, -0.8727F, 0.0F, 0.0F);
		body.addChild(tail);
		tail.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 3, 0.0F, false);
		
		leftWing = new Cuboid(this, 12, 0);
		leftWing.setRotationPoint(1.5F, -5.0F, 0.5F);
		setRotationAngle(leftWing, 0.3491F, 0.0F, 0.0F);
		body.addChild(leftWing);
		leftWing.addBox(-0.25F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);
		
		rightWing = new Cuboid(this, 20, 0);
		rightWing.setRotationPoint(-1.5F, -5.0F, 0.5F);
		setRotationAngle(rightWing, 0.3491F, 0.0F, 0.0F);
		body.addChild(rightWing);
		rightWing.addBox(-0.75F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);
		
		head = new Cuboid(this);
		head.setRotationPoint(0.0F, -5.5F, 1.0F);
		setRotationAngle(head, -0.4363F, 0.0F, 0.0F);
		body.addChild(head);
		head.addBox("head", -1.0F, -2.0F, -1.5F, 2, 3, 2, 0.0F, 0, 9);
		head.addBox("beak", -0.5F, -1.0F, -3.0F, 1, 1, 2, 0.0F, 17, 0);
	}
	
	@Override
	public void render(CrowEntity crow, float f, float f1, float f2, float f3, float f4, float f5) {
		setAngles(crow, f, f1, f2, f3, f4, f5);
		body.render(f5);
	}
	
	public void setRotationAngle(Cuboid cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
	
	@Override
	public void setAngles(CrowEntity crow, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setAngles(crow, f, f1, f2, f3, f4, f5);
		
		head.pitch = f4 * 0.017453292F;
		head.yaw = f3 * 0.017453292F;
		
		if (crow.isInAir()) {
			leftLeg.pitch = 0.55F;
			rightLeg.pitch = 0.55F;
			this.leftWing.roll = 0.0873F + f2;
			this.rightWing.roll = -0.0873F - f2;
		} else {
			leftLeg.pitch = -0.6109F + MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			rightLeg.pitch = -0.6109F + MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * f1;
			leftWing.roll = 0F;
			rightWing.roll = 0F;
		}
	}
	
	@Override
	public Cuboid getHead() {
		return head;
	}
}
