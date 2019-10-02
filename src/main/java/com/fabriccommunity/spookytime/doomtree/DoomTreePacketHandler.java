package com.fabriccommunity.spookytime.doomtree;

import java.util.Random;

import io.netty.util.internal.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class DoomTreePacketHandler {
	public static void accept(PacketContext context, PacketByteBuf buffer) {
		DoomTreePacket packet = buffer.readEnumConstant(DoomTreePacket.class);
		
		switch (packet) {
		case MIASMA:
			miasma(context, buffer);
			break;
		default:
			break;
		
		}
	}

	private static void miasma(PacketContext context, PacketByteBuf buffer) {
		final BlockPos pos = buffer.readBlockPos();
		final Random rand = ThreadLocalRandom.current();
		final int count = 4 + rand.nextInt(3);
		for (int i = 0; i < count; i++) {
			context.getPlayer().world.addParticle(ParticleTypes.SMOKE, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}
}