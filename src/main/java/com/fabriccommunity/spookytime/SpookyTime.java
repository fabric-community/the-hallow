package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyItems;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public final class SpookyTime implements ModInitializer
{
	public static final String MODID = "spookytime";
	
	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}

	@Override
	public void onInitialize()
	{
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyEntities.init();
	}
}
