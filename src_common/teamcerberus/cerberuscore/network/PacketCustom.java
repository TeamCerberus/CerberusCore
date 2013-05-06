package teamcerberus.cerberuscore.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class PacketCustom {
	private static BiMap<Integer, Class<? extends PacketCustom>>	idMap	= null;

	public static PacketCustom constructPacket(int packetId)
			throws ProtocolException, InstantiationException,
			IllegalAccessException {
		Class<? extends PacketCustom> clazz = idMap.get(Integer
				.valueOf(packetId));
		if (clazz == null) {
			throw new ProtocolException("Unknown Packet Id!");
		} else {
			return clazz.newInstance();
		}
	}

	public static class ProtocolException extends Exception {

		private static final long	serialVersionUID	= 6811937557832984996L;

		public ProtocolException() {}

		public ProtocolException(String message, Throwable cause) {
			super(message, cause);
		}

		public ProtocolException(String message) {
			super(message);
		}

		public ProtocolException(Throwable cause) {
			super(cause);
		}
	}

	public final int getPacketId() {
		if (idMap.inverse().containsKey(getClass())) {
			return idMap.inverse().get(getClass()).intValue();
		} else {
			throw new RuntimeException("Packet " + getClass().getSimpleName()
					+ " is missing a mapping!");
		}
	}

	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(getPacketId());
		write(out);
		return PacketDispatcher.getPacket(getChannelName(), out.toByteArray());
	}

	public void init() {
		@SuppressWarnings("unused")
		ImmutableBiMap.Builder<Integer, Class<? extends PacketCustom>> builder = ImmutableBiMap
				.builder();
	}

	public abstract void write(ByteArrayDataOutput out);

	public abstract void read(ByteArrayDataInput in);

	public abstract void execute(EntityPlayer player, Side side)
			throws ProtocolException;

	public abstract String getChannelName();
}
