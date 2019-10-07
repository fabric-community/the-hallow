package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.event.WitchTickCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;

public class SpookyEvents {
	public static void init() {
		WitchTickCallback.EVENT.register((witch) -> {
			if (!witch.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
				if (witch.getEntityWorld().hasRain(witch.getBlockPos().up())) {
					witch.damage(DamageSource.DROWN, 4.0F);
				} else if (witch.checkWaterState() && !witch.updateMovementInFluid(SpookyFluidTags.WITCH_WATER)) {
					witch.damage(DamageSource.DROWN, 4.0F);
				}
			}
			return ActionResult.PASS;
		});
	}
}
