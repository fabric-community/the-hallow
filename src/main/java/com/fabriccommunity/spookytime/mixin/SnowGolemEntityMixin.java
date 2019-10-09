package com.fabriccommunity.spookytime.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.fabriccommunity.spookytime.entity.SnowGolemEntityModifiers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Mixin(SnowGolemEntity.class)
public class SnowGolemEntityMixin implements SnowGolemEntityModifiers {

	@Inject(at = @At("TAIL"), method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V")
	public void writeCustomDataToTag(CompoundTag compoundTag, CallbackInfo ci) {
		Optional<BlockState> state = getHeadState();
		compoundTag.putString("PumpkinStateId", state.isPresent() ? Registry.BLOCK.getId(state.get().getBlock()).toString() : "");
	}

	@Inject(at = @At("TAIL"), method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V")
	public void readCustomDataFromTag(CompoundTag compoundTag, CallbackInfo ci) {
		// read custom pumpkin data
		if (compoundTag.containsKey("PumpkinStateId")) {
			setHeadState(Registry.BLOCK.get(new Identifier(compoundTag.getString("PumpkinStateId"))).getDefaultState());
		} else {
			setHeadState(Blocks.CARVED_PUMPKIN.getDefaultState()); // This is if the tag doesn't exist then set it to a normal pumpkin, it will be written to tag on save
		}
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker()V")
	protected void initDataTracker(CallbackInfo ci) {
		// Add custom DataTracker for pumpkin block head.
		getThis().getDataTracker().startTracking(SnowGolemEntityModifiers.HEAD_BLOCK_STATE, Optional.empty());
	}
	
	@Inject(at = @At(value = "INVOKE", target = "net/minecraft/entity/passive/SnowGolemEntity.setHasPumpkin(Z)V", shift = Shift.AFTER), method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Z")
	private void onSheared(CallbackInfoReturnable<Boolean> cir) {
		setHeadState(null); // This is converted to an optional before being added to DataTracker so don't worry.
	}

	@Override
	public Optional<BlockState> getHeadState() {
		return getThis().getDataTracker().get(HEAD_BLOCK_STATE);
	}

	@Override
	public void setHeadState(BlockState state) {
		if(!getThis().hasPumpkin()) {
			getThis().setHasPumpkin(true);
		}
		
		if(state == null) {
			getThis().setHasPumpkin(false);
		}
		getThis().getDataTracker().set(HEAD_BLOCK_STATE, Optional.ofNullable(state));
	}

	private SnowGolemEntity getThis() {
		return (SnowGolemEntity) (Object) this;
	}
}
