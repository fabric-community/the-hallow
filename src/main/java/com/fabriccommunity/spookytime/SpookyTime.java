package com.fabriccommunity.spookytime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fabriccommunity.spookytime.common.SpookyBiomes;
import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyDimensions;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import com.fabriccommunity.spookytime.common.SpookyItems;
import com.fabriccommunity.spookytime.common.SpookyWorldGen;

import net.fabricmc.api.ModInitializer;

public class SpookyTime implements ModInitializer
{
	public static final String MODID = "spookytime";
	public static final Logger LOGGER = LogManager.getLogger("SpookyTime");

	@Override
	public void onInitialize() {
		SpookyEntities.init();
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyBiomes.init();
		SpookyWorldGen.init();
		SpookyDimensions.init();
		
		LOGGER.info("Have a spoOOoOky time!");
	}
}
