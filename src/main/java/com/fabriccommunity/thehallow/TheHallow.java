package com.fabriccommunity.thehallow;

import com.fabriccommunity.thehallow.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import com.fabriccommunity.thehallow.registry.HallowBiomes;
import com.fabriccommunity.thehallow.registry.HallowBlockEntities;
import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.fabriccommunity.thehallow.registry.HallowCommands;
import com.fabriccommunity.thehallow.registry.HallowDimensions;
import com.fabriccommunity.thehallow.registry.HallowEnchantments;
import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.registry.HallowEvents;
import com.fabriccommunity.thehallow.registry.HallowFeatures;
import com.fabriccommunity.thehallow.registry.HallowFluidTags;
import com.fabriccommunity.thehallow.registry.HallowFluids;
import com.fabriccommunity.thehallow.registry.HallowInfusion;
import com.fabriccommunity.thehallow.registry.HallowItems;
import com.fabriccommunity.thehallow.registry.HallowNetworking;
import com.fabriccommunity.thehallow.registry.HallowSounds;
import com.fabriccommunity.thehallow.registry.HallowTags;
import com.fabriccommunity.thehallow.registry.HallowWorldGen;
import com.fabriccommunity.thehallow.world.HallowChunkGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TheHallow implements ModInitializer {
	public static final String MOD_ID = "thehallow";
	public static final Logger LOGGER = LogManager.getLogger("The Hallow");
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "group"), () -> new ItemStack(HallowItems.REAPERS_SCYTHE));
	public static final ItemGroup PUMPKINS = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "pumpkins"), () -> new ItemStack(HallowBlocks.WITCHED_JACK_O_LANTERN));
	
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		HallowConfig.sync(false);
		HallowEntities.init();
		HallowBlocks.init();
		HallowItems.init();
		HallowInfusion.init();
		HallowBlockEntities.init();
		HallowEnchantments.init();
		HallowCommands.init();
		HallowFeatures.init();
		HallowBiomes.init();
		HallowWorldGen.init();
		HallowChunkGeneratorType.init();
		HallowDimensions.init();
		HallowEvents.init();
		HallowSounds.init();
		HallowFluids.init();
		HallowFluidTags.init();
		HallowTags.init();
		HallowNetworking.init();
		HallowRecipes.init();
		HallowEvents.init();
	}
}
