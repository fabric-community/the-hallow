package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.common.*;
import dev.emi.trinkets.api.TrinketSlots;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpookyTime implements ModInitializer {

	public static final String MODID = "spookytime";
	public static final Logger LOGGER = LogManager.getLogger("SpookyTime");

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}

	@Override
	public void onInitialize() {
		SpookyBlocks.init();
		SpookyItems.init();
		SpookyCommands.init();
		SpookyBiomes.init();
		SpookyWorldGen.init();
		SpookyDimensions.init();
		SpookyEntities.init();

		TrinketSlots.addSubSlot("head", "mask", new Identifier("trinkets", "textures/item/empty_trinket_slot_mask.png"));
	}
}
