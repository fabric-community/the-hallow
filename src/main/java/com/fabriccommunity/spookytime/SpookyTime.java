package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

public class SpookyTime implements ModInitializer {
	
	public static final String MODID = "spookytime";
	public static final Logger LOGGER = LogManager.getLogger("SpookyTime");
	
	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
	
	@Override
	public void onInitialize() {
		SpookyConfig.sync(false);
		SpookyEntities.init();
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyCommands.init();
		SpookyBiomes.init();
		SpookyWorldGen.init();
		SpookyDimensions.init();
	}
}
