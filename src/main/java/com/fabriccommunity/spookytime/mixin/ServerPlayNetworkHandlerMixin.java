package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.component.CandyComponent;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyItems;
import dev.emi.trinkets.api.TrinketsApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.ChatMessageC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Handles trick or treat functionality when saying "trick or treat" to a villager.
 *
 * @author Emi
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
	private static final List<Item> costumeItems = new ArrayList<Item>();
	
	static {
		costumeItems.add(Items.ZOMBIE_HEAD);
		costumeItems.add(Items.CREEPER_HEAD);
		costumeItems.add(Items.DRAGON_HEAD);
		costumeItems.add(Items.PLAYER_HEAD);
		costumeItems.add(Items.SKELETON_SKULL);
		costumeItems.add(Items.WITHER_SKELETON_SKULL);
		costumeItems.add(Items.CARVED_PUMPKIN);
		costumeItems.add(SpookyItems.BLAZE_SKIRT);
	}
	
	@Shadow
	public ServerPlayerEntity player;
	
	@Inject(at = @At("TAIL"), method = "onChatMessage")
	public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo info) {
		if (packet.getChatMessage().toLowerCase().equals("trick or treat") && isPlayerWearingCostume(player)) {
			World world = player.getEntityWorld();
			Box box = new Box(player.x - 3, player.y - 3, player.z - 3, player.x + 3, player.y + 3, player.z + 3);
			List<VillagerEntity> villagers = world.getEntities(VillagerEntity.class, box);
			Iterator<VillagerEntity> iterator = villagers.iterator();
			while (iterator.hasNext()) {
				VillagerEntity entity = iterator.next();
				CandyComponent comp = SpookyEntities.CANDY.get(entity);
				if (comp.canGiveCandy(player)) {
					comp.setLastCandyTime(player, world.getTime());
					LootSupplier supplier = world.getServer().getLootManager().getSupplier(new Identifier("spookytime", "gameplay/trick_or_treat_candy"));
					LootContext context = (new LootContext.Builder((ServerWorld) world))
						.put(LootContextParameters.POSITION, new BlockPos(entity))
						.put(LootContextParameters.THIS_ENTITY, entity).setRandom(entity.getRand())
						.build(LootContextTypes.GIFT);
					List<ItemStack> stacks = supplier.getDrops(context);
					for(ItemStack stack: stacks){
						LookTargetUtil.give(entity, stack, player);
					}
				}
			}
		}
	}
	
	private boolean isPlayerWearingCostume(PlayerEntity player) {
		for (int i = 0; i < 4; i++) {
			if (costumeItems.contains(player.inventory.getArmorStack(i).getItem())) return true;
		}
		Inventory inv = TrinketsApi.getTrinketsInventory(player);
		for (int i = 0; i < inv.getInvSize(); i++) {
			if (costumeItems.contains(inv.getInvStack(i).getItem())) return true;
		}
		return false;
	}
}