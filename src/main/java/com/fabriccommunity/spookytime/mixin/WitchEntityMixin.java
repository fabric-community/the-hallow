package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.ActionResult;

import com.fabriccommunity.spookytime.event.WitchTickCallback;

/**
 * Causes witches to melt (get slowly damaged) while in water
 *
 * @author Will Toll
 */

@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin {
	@Inject(method = "tickMovement()V", at = @At("HEAD"), cancellable = true)
	public void tick(CallbackInfo info) {
		ActionResult result = WitchTickCallback.EVENT.invoker().tick((WitchEntity) (Object) this);
		if (result == ActionResult.FAIL) {
			info.cancel();
		}
	}
}
