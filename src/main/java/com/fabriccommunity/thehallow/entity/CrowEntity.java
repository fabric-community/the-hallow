package com.fabriccommunity.thehallow.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.entity.goal.EatBreadcrumbsGoal;
import com.fabriccommunity.thehallow.entity.goal.TemptBirdGoal;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedSounds;

import javax.annotation.Nullable;

public class CrowEntity extends AnimalEntity implements Flutterer {
	public float f1;
	public float f2;
	public float f3;
	public float f4;
	public float f5 = 1.0F;
	
	public CrowEntity(EntityType<? extends CrowEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 10, false);
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25D));
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(2, new EatBreadcrumbsGoal(HallowedBlocks.BREAD_CRUMBS, this, 1.25D, 40, 80));
		this.goalSelector.add(2, new TemptBirdGoal(this, 1.25D, false, Ingredient.ofItems(HallowedBlocks.BREAD_CRUMBS)));
		this.goalSelector.add(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributes().register(EntityAttributes.FLYING_SPEED);
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getAttributeInstance(EntityAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}
	
	@Override
	protected EntityNavigation createNavigation(World world) {
		BirdNavigation birdNavigation = new BirdNavigation(this, world);
		birdNavigation.setCanPathThroughDoors(false);
		birdNavigation.setCanSwim(true);
		birdNavigation.setCanEnterOpenDoors(true);
		return birdNavigation;
	}
	
	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.6F;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean handleFallDamage(float fallDistance, float damageModifier) {
		return false;
	}
	
	@Override
	protected void fall(double yVec, boolean onGround, BlockState state, BlockPos pos) {
	}
	
	@Override
	public boolean canBreedWith(AnimalEntity animal) {
		return false;
	}
	
	@Nullable
	@Override
	public PassiveEntity createChild(PassiveEntity entity) {
		return null;
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();
		this.flapWingsIThink();
	}
	
	private void flapWingsIThink() {
		this.f4 = this.f1;
		this.f3 = this.f2;
		this.f2 = (float) ((double) this.f2 + (double) (!this.onGround && !this.hasVehicle() ? 4 : -1) * 0.3D);
		this.f2 = MathHelper.clamp(this.f2, 0.0F, 1.0F);
		if (!this.onGround && this.f5 < 1.0F) {
			this.f5 = 1.0F;
		}
		
		this.f5 = (float) ((double) this.f5 * 0.9D);
		Vec3d vec3d_1 = this.getVelocity();
		if (!this.onGround && vec3d_1.y < 0.0D) {
			this.setVelocity(vec3d_1.multiply(1.0D, 0.6D, 1.0D));
		}
		
		this.f1 += this.f5 * 2.0F;
	}
	
	@Override
	protected float playFlySound(float distanceWalked) {
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return distanceWalked + this.f2 / 2.0F;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return HallowedSounds.CROW_AMBIENT;
	}
	
	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	@Override
	protected boolean hasWings() {
		return true;
	}
	
	public boolean isInAir() {
		return !this.onGround;
	}
}
