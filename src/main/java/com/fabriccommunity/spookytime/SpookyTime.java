package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.*;
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
        SpookyBiomes.init();
        SpookyDimensions.init();
	}
}
