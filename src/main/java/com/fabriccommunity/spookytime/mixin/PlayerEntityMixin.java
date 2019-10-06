package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.registry.SpookyItems;
import com.fabriccommunity.spookytime.util.PumpkinFoods;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@Shadow
	protected HungerManager hungerManager;
	
	@Inject(at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/item/Item;Lnet/minecraft/item/ItemStack;)V",
		shift = At.Shift.AFTER
	), method = "eatFood")
	private void addPumpkinRingBonus(World world, ItemStack itemStack, CallbackInfoReturnable<ItemStack> info) {
		PlayerEntity playerEntity = (PlayerEntity) (Object) this;
		TrinketComponent trinketPlayer = TrinketsApi.getTrinketComponent(playerEntity);
		
		ItemStack mainHandStack = trinketPlayer.getStack("hand:ring");
		ItemStack offHandStack = trinketPlayer.getStack("offhand:ring");
		Item item = itemStack.getItem();
		
		if (mainHandStack.getItem().equals(SpookyItems.PUMPKIN_RING) || offHandStack.getItem().equals(SpookyItems.PUMPKIN_RING)) {
			if (item.isFood()) {
				if (PumpkinFoods.isItemPumpkin(item)) {
					FoodComponent foodComponent = item.getFoodComponent();
					int extraHunger = (int) Math.ceil(foodComponent.getHunger() * .25);
					this.hungerManager.add(extraHunger, 1);
				}
			}
		}
	}
}
