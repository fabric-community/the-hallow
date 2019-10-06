package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.event.WitchTickCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;

public class SpookyEvents {
	public static void init() {

		WitchTickCallback.EVENT.register((witch) -> {
			if (witch.getEntityWorld().hasRain(witch.getBlockPos().up())) {
				witch.damage(DamageSource.DROWN, 1.0F);
				witch.setDrinking(false);
			} else if (witch.checkWaterState() && !witch.updateMovementInFluid(SpookyFluidTags.WITCH_WATER)) {
				witch.damage(DamageSource.DROWN, 1.0F);
				witch.setDrinking(false);
			}
			return ActionResult.PASS;
		});

	}
}
