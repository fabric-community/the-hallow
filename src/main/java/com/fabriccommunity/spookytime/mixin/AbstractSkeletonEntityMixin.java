package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;

import com.fabriccommunity.spookytime.util.MixinHelpers;
import com.fabriccommunity.spookytime.SpookyConfig;
import com.fabriccommunity.spookytime.registry.SpookyItems;

import java.util.Random;

/**
 * Handles skeletons sometimes spawning wearing pumpkins on their heads.
 *
 * @author Indigo Amann
 */
@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {
	@Redirect(method = "initialize(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnType;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/entity/EntityData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
	private ItemStack getStack(AbstractSkeletonEntity entity, EquipmentSlot equipmentSlot) {
		return MixinHelpers.getEquippedOrPumpkin(entity, equipmentSlot);
	}
	
	@Inject(method = "initEquipment(Lnet/minecraft/world/LocalDifficulty;)V", at = @At(value = "TAIL"))
	protected void initEquipment(LocalDifficulty ld, CallbackInfo cb) {
		Random random = new Random();
		if (SpookyConfig.TrumpetSkeleton.enabled && random.nextInt(SpookyConfig.TrumpetSkeleton.chance) == 0) {
			//noinspection ConstantConditions
			((AbstractSkeletonEntity) (Object) this).setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(SpookyItems.SPOOKY_TRUMPET));
		}
	}
}
