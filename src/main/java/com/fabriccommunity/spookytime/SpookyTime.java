package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpookyTime implements ModInitializer {
	public static final String MOD_ID = "spookytime";
	public static final Logger LOGGER = LogManager.getLogger("Spooky Time");
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "group"), () -> new ItemStack(SpookyItems.REAPERS_SCYTHE));

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		SpookyConfig.sync(false);
		SpookyEntities.init();
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyEnchantments.init();
		SpookyCommands.init();
		SpookyFeatures.init();
		SpookyBiomes.init();
		SpookyWorldGen.init();
		SpookyDimensions.init();
		SpookyFluids.init();
		SpookyFluidTags.init();
		SpookyTags.init();
		SpookyNetworking.init();
	}
}
