package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;

import com.fabriccommunity.thehallow.HallowedConfig;
import com.fabriccommunity.thehallow.util.MixinHelpers;

/**
 * Gives Endermen a chance to hold pumpkin blocks when spawning.
 *
 * @author Indigo Amann
 */
@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
	@Inject(method = "initialize(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnType;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/entity/EntityData;", at = @At("RETURN"))
	public void initialize(IWorld iWorld, LocalDifficulty localDifficulty, SpawnType spawnType, EntityData entityData, CompoundTag compoundTag, CallbackInfoReturnable<EntityData> cir) {
		if (((Object) this) instanceof EndermanEntity && HallowedConfig.PumpkinMobs.endermen && MixinHelpers.RANDOM.nextInt(10) == 0) {
			((EndermanEntity) (Object) this).setCarriedBlock((MixinHelpers.RANDOM.nextBoolean() ? Blocks.PUMPKIN : (MixinHelpers.RANDOM.nextBoolean() ? Blocks.CARVED_PUMPKIN : Blocks.JACK_O_LANTERN)).getDefaultState());
		}
	}
}
