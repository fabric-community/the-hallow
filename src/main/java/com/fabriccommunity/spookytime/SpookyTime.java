package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import com.fabriccommunity.spookytime.common.SpookyItems;
import dev.emi.trinkets.api.TrinketSlots;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SpookyTime implements ModInitializer
{
	public static final String MODID = "spookytime";

	@Override
	public void onInitialize() {
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyEntities.init();

		TrinketSlots.addSubSlot("head", "mask", new Identifier("trinkets", "textures/item/empty_trinket_slot_mask.png"));
	}
}
