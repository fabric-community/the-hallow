package com.fabriccommunity.thehallow.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;
import io.netty.buffer.Unpooled;

public class HallowedNetworking {
	public static final Identifier SHOW_FLOATING_ITEM_S2C = TheHallow.id("show_floating_item_s2c");
	
	private HallowedNetworking() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static PacketByteBuf createShowFloatingItemPacket(Item item) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeVarInt(Registry.ITEM.getRawId(item));
		return buf;
	}
}
