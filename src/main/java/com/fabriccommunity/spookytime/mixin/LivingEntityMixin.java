package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.enchantment.BeheadingEnchantment;
import com.fabriccommunity.spookytime.enchantment.LifestealEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;

/**
 * Implement Beheading and Lifesteal.
 *
 * @author vini2003
 */

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
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
}
