package com.fabriccommunity.spookytime.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.Bird;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.control.ParrotMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.entity.goal.EatBreadcrumbsGoal;
import com.fabriccommunity.spookytime.entity.goal.TemptBirdGoal;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookySounds;

import javax.annotation.Nullable;
import java.util.Random;

public class CrowEntity extends AnimalEntity implements Bird {
	public float field_6818;
	public float field_6819;
	public float field_6827;
	public float field_6829;
	public float field_6824 = 1.0F;
	
	public CrowEntity(EntityType<? extends CrowEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new ParrotMoveControl(this);
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25D));
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(2, new EatBreadcrumbsGoal(SpookyBlocks.BREAD_CRUMBS, this, 1.25D, 40, 80));
		this.goalSelector.add(2, new FlyAroundGoal(this, 1.0D));
		this.goalSelector.add(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
		this.goalSelector.add(3, new TemptBirdGoal(this, 1.25D, false, Ingredient.ofItems(SpookyBlocks.BREAD_CRUMBS)));
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeContainer().register(EntityAttributes.FLYING_SPEED);
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getAttributeInstance(EntityAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}
	
	@Override
	protected EntityNavigation createNavigation(World world_1) {
		BirdNavigation birdNavigation_1 = new BirdNavigation(this, world_1);
		birdNavigation_1.setCanPathThroughDoors(false);
		birdNavigation_1.setCanSwim(true);
		birdNavigation_1.setCanEnterOpenDoors(true);
		return birdNavigation_1;
	}
	
	@Override
	protected float getActiveEyeHeight(EntityPose entityPose_1, EntityDimensions entityDimensions_1) {
		return entityDimensions_1.height * 0.6F;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack itemStack_1) {
		return false;
	}
	
	@Override
	public void handleFallDamage(float float_1, float float_2) {
	}
	
	@Override
	protected void fall(double double_1, boolean boolean_1, BlockState blockState_1, BlockPos blockPos_1) {
	}
	
	@Override
	public boolean canBreedWith(AnimalEntity animalEntity_1) {
		return false;
	}
	
	@Nullable
	@Override
	public PassiveEntity createChild(PassiveEntity passiveEntity_1) {
		return null;
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();
		this.flapWingsIThink();
	}
	
	private void flapWingsIThink() {
		this.field_6829 = this.field_6818;
		this.field_6827 = this.field_6819;
		this.field_6819 = (float)((double)this.field_6819 + (double)(!this.onGround && !this.hasVehicle() ? 4 : -1) * 0.3D);
		this.field_6819 = MathHelper.clamp(this.field_6819, 0.0F, 1.0F);
		if (!this.onGround && this.field_6824 < 1.0F) {
			this.field_6824 = 1.0F;
		}
		
		this.field_6824 = (float)((double)this.field_6824 * 0.9D);
		Vec3d vec3d_1 = this.getVelocity();
		if (!this.onGround && vec3d_1.y < 0.0D) {
			this.setVelocity(vec3d_1.multiply(1.0D, 0.6D, 1.0D));
		}
		
		this.field_6818 += this.field_6824 * 2.0F;
	}
	
	@Override
	protected float calculateAerialStepDelta(float float_1) {
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return float_1 + this.field_6819 / 2.0F;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return SpookySounds.CROW_AMBIENT;
	}
	
	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	@Override
	protected boolean method_5776() {
		return true;
	}
	
	public boolean isInAir() {
		return !this.onGround;
	}
}
