package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.common.SpookyItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractSkeletonEntity.class)
public class MixinAbstractSkeletonEntity {
    @Inject(method = "initEquipment", at = @At(value = "TAIL"))
    protected void initEquipment(LocalDifficulty ld, CallbackInfo cb) {
        Random random = new Random();
        if (random.nextInt(3) == 0) {
            //noinspection ConstantConditions
            ((AbstractSkeletonEntity) (Object) this).setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(SpookyItems.SPOOKY_TRUMPET));
        }
    }
}
