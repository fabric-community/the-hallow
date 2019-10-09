package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.enchantment.BeheadingEnchantment;
import com.fabriccommunity.spookytime.enchantment.LifestealEnchantment;

/**
 * Implement Beheading and Lifesteal, along with letting golden candy corn take durability when eaten instead of having it stack shrunken.
 *
 * @author vini2003, B0undarybreaker
 */

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow
	public abstract void sendToolBreakStatus(Hand hand_1);
	
	@Shadow
	public abstract Hand getActiveHand();
	
	@Inject(method = "drop", at = @At("HEAD"))
	public void drop(DamageSource damageSource, CallbackInfo info) {
		LivingEntity livingEntity = (LivingEntity) (Object) this;
		if (damageSource.getSource() instanceof LivingEntity && livingEntity.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) && BeheadingEnchantment.hasBeheading((LivingEntity) damageSource.getSource())) {
			if (BeheadingEnchantment.getHead(damageSource)) {
				if (livingEntity.getType() == EntityType.WITHER_SKELETON) {
					livingEntity.dropStack(new ItemStack(Items.WITHER_SKELETON_SKULL));
				} else if (livingEntity.getType() == EntityType.SKELETON) {
					livingEntity.dropStack(new ItemStack(Items.SKELETON_SKULL));
				} else if (livingEntity.getType() == EntityType.ZOMBIE) {
					livingEntity.dropStack(new ItemStack(Items.ZOMBIE_HEAD));
				} else if (livingEntity.getType() == EntityType.CREEPER) {
					livingEntity.dropStack(new ItemStack(Items.CREEPER_HEAD));
				} else if (livingEntity.getType() == EntityType.PLAYER) {
					livingEntity.dropStack(new ItemStack(Items.PLAYER_HEAD));
				}
			}
		}
	}
	
	@Inject(method = "applyDamage", at = @At("RETURN"))
	public void applyDamage(DamageSource damageSource, float damage, CallbackInfo info) {
		LivingEntity attacked = (LivingEntity) (Object) this;
		if (damageSource.getSource() instanceof LivingEntity && attacked.getHealth() < damage) {
			LivingEntity attacker = (LivingEntity) damageSource.getSource();
			if (!attacked.isInvulnerableTo(damageSource)) {
				float health = LifestealEnchantment.getLifeWithSteal(damageSource, damage, attacked);
				if (health != 0) {
					attacker.setHealth(health);
				}
			}
		}
	}
	
	@Inject(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"), cancellable = true)
	public void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
		if (stack.isDamageable()) {
			stack.damage(1, (LivingEntity) (Object) this, (entity) -> entity.sendToolBreakStatus(getActiveHand()));
			info.setReturnValue(stack);
		}
	}
}
