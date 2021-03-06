package com.fabriccommunity.thehallow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

import com.fabriccommunity.thehallow.compat.libcd.HallowTweaker;
import com.fabriccommunity.thehallow.registry.HallowedBiomes;
import com.fabriccommunity.thehallow.registry.HallowedBlockEntities;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedCommands;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;
import com.fabriccommunity.thehallow.registry.HallowedEnchantments;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import com.fabriccommunity.thehallow.registry.HallowedEvents;
import com.fabriccommunity.thehallow.registry.HallowedFeatures;
import com.fabriccommunity.thehallow.registry.HallowedFluids;
import com.fabriccommunity.thehallow.registry.HallowedItems;
import com.fabriccommunity.thehallow.registry.HallowedNetworking;
import com.fabriccommunity.thehallow.registry.HallowedRecipes;
import com.fabriccommunity.thehallow.registry.HallowedSounds;
import com.fabriccommunity.thehallow.registry.HallowedTags;
import com.fabriccommunity.thehallow.registry.HallowedWorldGen;
import com.fabriccommunity.thehallow.registry.MinecraftItems;
import com.fabriccommunity.thehallow.world.HallowedChunkGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TheHallow implements ModInitializer {
	public static final String MOD_ID = "thehallow";
	public static final Logger LOGGER = LogManager.getLogger("The Hallow");
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(HallowedItems.REAPERS_SCYTHE));
	public static final ItemGroup PUMPKINS = FabricItemGroupBuilder.build(id("pumpkins"), () -> new ItemStack(HallowedBlocks.WITCHED_PUMPKIN));
	public static final SignType DEADWOOD_SIGN_TYPE = new SignTypeAccess("thehallow:deadwood");
	
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		HallowedConfig.sync();
		HallowedEntities.init();
		HallowedBlocks.init();
		HallowedItems.init();
		HallowedRecipes.init();
		HallowedBlockEntities.init();
		HallowedEnchantments.init();
		HallowedCommands.init();
		HallowedFeatures.init();
		HallowedBiomes.init();
		HallowedWorldGen.init();
		HallowedChunkGeneratorType.init();
		HallowedDimensions.init();
		HallowedEvents.init();
		HallowedSounds.init();
		HallowedFluids.init();
		HallowedTags.init();
		HallowedNetworking.init();
		HallowedEvents.init();
		
		MinecraftItems.init();

		if (FabricLoader.getInstance().isModLoaded("libcd")) {
			HallowTweaker.init();
		}
	}
	
	private static class SignTypeAccess extends SignType {
		public SignTypeAccess(String name) {
			super(name);
		}
	}
}
