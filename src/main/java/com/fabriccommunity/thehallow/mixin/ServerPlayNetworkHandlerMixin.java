package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.ChatMessageC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.HallowedConfig;
import com.fabriccommunity.thehallow.component.CandyComponent;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import com.fabriccommunity.thehallow.registry.HallowedTags;
import dev.emi.trinkets.api.TrinketsApi;

import java.util.Iterator;
import java.util.List;

/**
 * Handles trick or treat functionality when saying "trick or treat" to a villager.
 *
 * @author Emi
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
	@Shadow
	public ServerPlayerEntity player;
	
	@Inject(at = @At("TAIL"), method = "onChatMessage(Lnet/minecraft/server/network/packet/ChatMessageC2SPacket;)V")
	public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo info) {
		if (packet.getChatMessage().toLowerCase().contains("trick or treat")) {
			if (isPlayerWearingCostume(player)) {
				World world = player.getEntityWorld();
				Box box = new Box(player.getX() - 3, player.getY() - 3, player.getZ() - 3, player.getX() + 3, player.getY() + 3, player.getZ() + 3);
				List<VillagerEntity> villagers = world.getNonSpectatingEntities(VillagerEntity.class, box);
				Iterator<VillagerEntity> iterator = villagers.iterator();
				if (iterator.hasNext()) {
					boolean trick = HallowedConfig.TrickOrTreating.enableTricks && world.random.nextInt(HallowedConfig.TrickOrTreating.trickChance) == 0;
					if (trick) {
						player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
						player.sendChatMessage(new TranslatableText("text.thehallow.trick"), MessageType.GAME_INFO);
					} else {
						player.playSound(SoundEvents.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
						player.sendChatMessage(new TranslatableText("text.thehallow.treat"), MessageType.GAME_INFO);
					}
					while (iterator.hasNext()) {
						VillagerEntity entity = iterator.next();
						if (!entity.isBaby()) {
							if (trick) {
								WitchEntity witch = new WitchEntity(EntityType.WITCH, world);
								witch.copyPositionAndRotation(entity);
								witch.setAttacker(player);
								entity.remove();
								world.spawnEntity(witch);
							} else {
								CandyComponent comp = HallowedEntities.CANDY.get(entity);
								if (comp.canGiveCandy(player)) {
									comp.setLastCandyTime(player, world.getTime());
									LootTable supplier = world.getServer().getLootManager().getSupplier(new Identifier("thehallow", "gameplay/trick_or_treat_candy"));
									LootContext context = (new LootContext.Builder((ServerWorld) world))
										.put(LootContextParameters.POSITION, new BlockPos(entity))
										.put(LootContextParameters.THIS_ENTITY, entity).setRandom(entity.getRandom())
										.build(LootContextTypes.GIFT);
									List<ItemStack> stacks = supplier.getDrops(context);
									for (ItemStack stack : stacks) {
										LookTargetUtil.give(entity, stack, player);
									}
								}
							}
						}
					}
				} else {
					player.sendChatMessage(new TranslatableText("text.thehallow.no_one_nearby"), MessageType.GAME_INFO);
				}
			} else {
				player.sendChatMessage(new TranslatableText("text.thehallow.not_wearing_costume"), MessageType.GAME_INFO);
			}
		}
	}
	
	private boolean isPlayerWearingCostume(PlayerEntity player) {
		for (int i = 0; i < 4; i++) {
			if (HallowedTags.Items.COSTUMES.contains(player.inventory.armor.get(i).getItem()))
				return true;
		}
		Inventory inv = TrinketsApi.getTrinketsInventory(player);
		for (int i = 0; i < inv.getInvSize(); i++) {
			if (HallowedTags.Items.COSTUMES.contains(inv.getInvStack(i).getItem())) return true;
		}
		return false;
	}
}
