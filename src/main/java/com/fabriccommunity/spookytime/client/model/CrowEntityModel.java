package com.fabriccommunity.spookytime.client.model;

import com.fabriccommunity.spookytime.entity.CrowEntity;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;

public class CrowEntityModel extends EntityModel<CrowEntity> {
	private final Cuboid body;
	private final Cuboid left_leg;
	private final Cuboid right_leg;
	private final Cuboid tail;
	private final Cuboid left_wing;
	private final Cuboid right_wing;
	private final Cuboid head;

	public CrowEntityModel() {
		textureWidth = 32;
		textureHeight = 32;

		body = new Cuboid(this, 0, 0);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(body, 0.6109F, 0.0F, 0.0F);
		body.addBox(-1.5F, -5.5F, -1.0F, 3, 6, 3, 0.0F, false);

		left_leg = new Cuboid(this);
		left_leg.setRotationPoint(1.0F, 0.0F, 1.0F);
		setRotationAngle(left_leg, -0.6109F, 0.0F, 0.0F);
		body.addChild(left_leg);
		left_leg.addBox("left_leg", -0.75F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 0, 9);
		left_leg.addBox("left_foot", -0.75F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 8, 0);

		right_leg = new Cuboid(this);
		right_leg.setRotationPoint(-1.0F, 0.0F, 1.0F);
		setRotationAngle(right_leg, -0.6109F, 0.0F, 0.0F);
		body.addChild(right_leg);
		right_leg.addBox("right_leg", -0.25F, -0.5F, 0.0F, 1, 3, 0, 0.0F, 0, 0);
		right_leg.addBox("right_foot", -0.25F, 2.5F, -1.0F, 1, 0, 1, 0.0F, 8, 1);

		tail = new Cuboid(this, 12, 0);
		tail.setRotationPoint(0.0F, 0.0F, 1.5F);
		setRotationAngle(tail, -0.8727F, 0.0F, 0.0F);
		body.addChild(tail);
		tail.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 3, 0.0F, false);

		left_wing = new Cuboid(this, 8, 9);
		left_wing.setRotationPoint(1.5F, -5.0F, 0.5F);
		setRotationAngle(left_wing, 0.3491F, 0.0F, 0.0F);
		body.addChild(left_wing);
		left_wing.addBox(0.0F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);

		right_wing = new Cuboid(this, 0, 9);
		right_wing.setRotationPoint(-1.5F, -5.0F, 0.5F);
		setRotationAngle(right_wing, 0.3491F, 0.0F, 0.0F);
		body.addChild(right_wing);
		right_wing.addBox(-1.0F, 0.0F, -2.0F, 1, 5, 3, 0.0F, false);

		head = new Cuboid(this);
		head.setRotationPoint(0.0F, -5.5F, 1.0F);
		setRotationAngle(head, -0.4363F, 0.0F, 0.0F);
		body.addChild(head);
		head.addBox("head", -1.0F, -3.0F, -1.5F, 2, 4, 2, 0.0F, 13, 4);
		head.addBox("beak", -0.5F, -1.5F, -3.0F, 1, 1, 2, 0.0F, 5, 9);
	}

	@Override
	public void render(CrowEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}
	
	public void setRotationAngle(Cuboid cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
}
