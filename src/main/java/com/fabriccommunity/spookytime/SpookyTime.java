package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import com.fabriccommunity.spookytime.doomtree.DoomTree;
import com.fabriccommunity.spookytime.registry.SpookyBiomes;
import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyCommands;
import com.fabriccommunity.spookytime.registry.SpookyDimensions;
import com.fabriccommunity.spookytime.registry.SpookyEnchantments;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyEvents;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.registry.SpookyFluidTags;
import com.fabriccommunity.spookytime.registry.SpookyFluids;
import com.fabriccommunity.spookytime.registry.SpookyInfusion;
import com.fabriccommunity.spookytime.registry.SpookyItems;
import com.fabriccommunity.spookytime.registry.SpookyNetworking;
import com.fabriccommunity.spookytime.registry.SpookySounds;
import com.fabriccommunity.spookytime.registry.SpookyTags;
import com.fabriccommunity.spookytime.registry.SpookyWorldGen;
import com.fabriccommunity.spookytime.world.SpookyChunkGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpookyTime implements ModInitializer {
	public static final String MOD_ID = "spookytime";
	public static final Logger LOGGER = LogManager.getLogger("Spooky Time");
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "group"), () -> new ItemStack(SpookyItems.REAPERS_SCYTHE));
	public static final ItemGroup PUMPKINS = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "pumpkins"), () -> new ItemStack(SpookyBlocks.WITCHED_JACK_O_LANTERN));
	
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		SpookyConfig.sync(false);
		SpookyEntities.init();
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyInfusion.init();
		SpookyBlockEntities.init();
		SpookyEnchantments.init();
		SpookyCommands.init();
		SpookyFeatures.init();
		SpookyBiomes.init();
		SpookyWorldGen.init();
		SpookyChunkGeneratorType.init();
		SpookyDimensions.init();
		SpookyEvents.init();
		SpookySounds.init();
		SpookyFluids.init();
		SpookyFluidTags.init();
		SpookyTags.init();
		SpookyNetworking.init();
		SpookyRecipes.init();
		SpookyEvents.init();
		DoomTree.init();
	}
}
