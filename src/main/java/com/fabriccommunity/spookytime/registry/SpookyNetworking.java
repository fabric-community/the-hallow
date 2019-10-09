package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import io.netty.buffer.Unpooled;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class SpookyNetworking {
	public static final Identifier SHOW_FLOATING_ITEM_S2C = SpookyTime.id("show_floating_item_s2c");

	private SpookyNetworking() {
		// NO-OP
	}

	// Server-side packet handlers and other common init
	public static void init() {
	}

	public static PacketByteBuf createShowFloatingItemPacket(Item item) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeVarInt(Registry.ITEM.getRawId(item));
		return buf;
	}
}
