package com.fabriccommunity.spookytime.entity.goal;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

import java.util.EnumSet;

public class TemptBirdGoal extends Goal {
	private static final TargetPredicate TEMPTING_ENTITY_PREDICATE = (new TargetPredicate()).setBaseMaxDistance(10.0D).includeInvulnerable().includeTeammates().ignoreEntityTargetRules().includeHidden();
	protected final MobEntityWithAi mob;
	private final double speed;
	private final Ingredient food;
	private final boolean canBeScared;
	protected PlayerEntity closestPlayer;
	private double lastPlayerX;
	private double lastPlayerY;
	private double lastPlayerZ;
	private double lastPlayerPitch;
	private double lastPlayerYaw;
	private int cooldown;
	private boolean active;
	
	public TemptBirdGoal(MobEntityWithAi mobEntityWithAi, double speed, boolean canBeScared, Ingredient food) {
		this.mob = mobEntityWithAi;
		this.speed = speed;
		this.food = food;
		this.canBeScared = canBeScared;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
		if (!(mobEntityWithAi.getNavigation() instanceof BirdNavigation)) {
			throw new IllegalArgumentException("Unsupported mob type for TemptBirdGoal");
		}
	}
	
	@Override
	public boolean canStart() {
		if (this.cooldown > 0) {
			--this.cooldown;
			return false;
		} else {
			this.closestPlayer = this.mob.world.getClosestPlayer(TEMPTING_ENTITY_PREDICATE, this.mob);
			if (this.closestPlayer == null) {
				return false;
			} else {
				return this.isTemptedBy(this.closestPlayer.getMainHandStack()) || this.isTemptedBy(this.closestPlayer.getOffHandStack());
			}
		}
	}
	
	protected boolean isTemptedBy(ItemStack stack) {
		return this.food.method_8093(stack);
	}
	
	@Override
	public boolean shouldContinue() {
		if (this.canBeScared()) {
			if (this.mob.squaredDistanceTo(this.closestPlayer) < 80.0D) {
				if (this.closestPlayer.squaredDistanceTo(this.lastPlayerX, this.lastPlayerY, this.lastPlayerZ) > 0.010000000000000002D) {
					return false;
				}
				
				if (Math.abs((double) this.closestPlayer.pitch - this.lastPlayerPitch) > 5.0D || Math.abs((double) this.closestPlayer.yaw - this.lastPlayerYaw) > 5.0D) {
					return false;
				}
			} else {
				this.lastPlayerX = this.closestPlayer.x;
				this.lastPlayerY = this.closestPlayer.y;
				this.lastPlayerZ = this.closestPlayer.z;
			}
			
			this.lastPlayerPitch = this.closestPlayer.pitch;
			this.lastPlayerYaw = this.closestPlayer.yaw;
		}
		
		return this.canStart();
	}
	
	protected boolean canBeScared() {
		return this.canBeScared;
	}
	
	@Override
	public void start() {
		this.lastPlayerX = this.closestPlayer.x;
		this.lastPlayerY = this.closestPlayer.y;
		this.lastPlayerZ = this.closestPlayer.z;
		this.active = true;
	}
	
	@Override
	public void stop() {
		this.closestPlayer = null;
		this.mob.getNavigation().stop();
		this.cooldown = 100;
		this.active = false;
	}
	
	@Override
	public void tick() {
		this.mob.getLookControl().lookAt(this.closestPlayer, (float) (this.mob.method_5986() + 20), (float) this.mob.getLookPitchSpeed());
		if (this.mob.squaredDistanceTo(this.closestPlayer) < 6.25D) {
			this.mob.getNavigation().stop();
		} else {
			this.mob.getNavigation().startMovingTo(this.closestPlayer, this.speed);
		}
		
	}
	
	public boolean isActive() {
		return this.active;
	}
}
