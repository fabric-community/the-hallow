package com.fabriccommunity.thehallow.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MummyEntity extends ZombieEntity {
	public MummyEntity(EntityType<? extends MummyEntity> type, World world) {
		super(type, world);
	}
	
	protected boolean burnsInDaylight() {
		return false;
	}
	
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(2.5D);
	}
	
	protected boolean canConvertInWater() {
		return false;
	}
	
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}
}
