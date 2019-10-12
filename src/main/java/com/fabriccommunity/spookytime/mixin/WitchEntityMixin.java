package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.event.WitchTickCallback;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Causes witches to melt (get slowly damaged) while in water
 *
 * @author Will Toll
 */

@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin {
	@Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
	public void tick(CallbackInfo info) {
		ActionResult result = WitchTickCallback.EVENT.invoker().tick( (WitchEntity) (Object) this );
		if (result == ActionResult.FAIL) {
			info.cancel();
		}
	}
}
