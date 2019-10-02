package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyCommands;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import com.fabriccommunity.spookytime.common.SpookyItems;

import net.fabricmc.api.ModInitializer;

public class SpookyTime implements ModInitializer {
	
	public static final String MODID = "spookytime";

	@Override
	public void onInitialize() {
		SpookyEntities.init();
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyCommands.init();
	}
}
