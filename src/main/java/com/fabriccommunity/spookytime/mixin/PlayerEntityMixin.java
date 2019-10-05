package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyItems;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	private static final ArrayList<Item> PUMPKIN_FOODS = new ArrayList<>();

	static {
		PUMPKIN_FOODS.add(Items.PUMPKIN_PIE);
		PUMPKIN_FOODS.add(SpookyItems.BAKED_PUMPKIN_SEEDS);
		PUMPKIN_FOODS.add(SpookyItems.PUMPKIN_STEW);
		PUMPKIN_FOODS.add(SpookyItems.PUMPKIN_CANDY);
	}

	@Shadow protected HungerManager hungerManager;

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

		if(mainHandStack.getItem().equals(SpookyItems.PUMPKIN_RING) || offHandStack.getItem().equals(SpookyItems.PUMPKIN_RING)) {
			if (item.isFood()) {
				if(PUMPKIN_FOODS.contains(item)) {
					FoodComponent foodComponent = item.getFoodComponent();
					int extraHunger = (int) (foodComponent.getHunger() * .25);
					this.hungerManager.add(extraHunger, 1);
				}
			}
		}
	}
}
