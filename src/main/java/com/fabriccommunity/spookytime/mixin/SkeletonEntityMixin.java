package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyItems;
import com.fabriccommunity.spookytime.registry.SpookySounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkeletonEntity.class)
public class SkeletonEntityMixin {
	@Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
	protected void getAmbientSound(CallbackInfoReturnable<SoundEvent> cb) {
		//noinspection ConstantConditions
		if (((SkeletonEntity) (Object) this).getEquippedStack(EquipmentSlot.MAINHAND).getItem() == SpookyItems.SPOOKY_TRUMPET) {
			cb.setReturnValue(SpookySounds.DOOT);
		}
	}
}
