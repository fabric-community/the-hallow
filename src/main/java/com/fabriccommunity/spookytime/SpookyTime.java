package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyItems;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import net.fabricmc.api.ModInitializer;

public class SpookyTime implements ModInitializer
{

	
	public static final String MODID = "spookytime";

	@Override
	public void onInitialize()
	{
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyEntities.init();
	}
}
