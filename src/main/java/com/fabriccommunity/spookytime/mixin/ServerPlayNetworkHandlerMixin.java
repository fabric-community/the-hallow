package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.SpookyConfig;
import com.fabriccommunity.spookytime.component.CandyComponent;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyTags;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

	@Inject(at = @At("TAIL"), method = "onChatMessage")
	public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo info) {
		if (packet.getChatMessage().toLowerCase().contains("trick or treat")) {
			if (isPlayerWearingCostume(player)) {
				World world = player.getEntityWorld();
				Box box = new Box(player.x - 3, player.y - 3, player.z - 3, player.x + 3, player.y + 3, player.z + 3);
				List<VillagerEntity> villagers = world.getEntities(VillagerEntity.class, box);
				Iterator<VillagerEntity> iterator = villagers.iterator();
				if (iterator.hasNext()) {
					boolean trick = SpookyConfig.TrickOrTreating.enableTricks && world.random.nextInt(SpookyConfig.TrickOrTreating.trickChance) == 0;
					if (trick) {
						player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
						player.sendChatMessage(new TranslatableText("text.spookytime.trick"), MessageType.GAME_INFO);
					} else {
						player.playSound(SoundEvents.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
						player.sendChatMessage(new TranslatableText("text.spookytime.treat"), MessageType.GAME_INFO);
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
								CandyComponent comp = SpookyEntities.CANDY.get(entity);
								if (comp.canGiveCandy(player)) {
									comp.setLastCandyTime(player, world.getTime());
									LootSupplier supplier = world.getServer().getLootManager().getSupplier(new Identifier("spookytime", "gameplay/trick_or_treat_candy"));
									LootContext context = (new LootContext.Builder((ServerWorld) world))
										.put(LootContextParameters.POSITION, new BlockPos(entity))
										.put(LootContextParameters.THIS_ENTITY, entity).setRandom(entity.getRand())
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
					player.sendChatMessage(new TranslatableText("text.spookytime.no_one_nearby"), MessageType.GAME_INFO);
				}
			} else {
				player.sendChatMessage(new TranslatableText("text.spookytime.not_wearing_costume"), MessageType.GAME_INFO);
			}
		}
	}

	private boolean isPlayerWearingCostume(PlayerEntity player) {
		for (int i = 0; i < 4; i++) {
			if (SpookyTags.COSTUMES.contains(player.inventory.getArmorStack(i).getItem()))
				return true;
		}
		Inventory inv = TrinketsApi.getTrinketsInventory(player);
		for (int i = 0; i < inv.getInvSize(); i++) {
			if (SpookyTags.COSTUMES.contains(inv.getInvStack(i).getItem())) return true;
		}
		return false;
	}
}
