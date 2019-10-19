package com.fabriccommunity.thehallow.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.ActionResult;

/**
 * Creates an event for whenever witches are ticking
 *
 * @author Will Toll
 */

public interface WitchTickCallback {
	Event<WitchTickCallback> EVENT = EventFactory.createArrayBacked(WitchTickCallback.class,
		(listeners) -> (witch) -> {
			for (WitchTickCallback event : listeners) {
				ActionResult result = event.tick(witch);
				if (result != ActionResult.PASS) {
					return result;
				}
			}
			return ActionResult.PASS;
		});
	
	ActionResult tick(WitchEntity witch);
}
