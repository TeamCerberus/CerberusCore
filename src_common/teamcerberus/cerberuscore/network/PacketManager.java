package teamcerberus.cerberuscore.network;

import java.util.HashMap;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

public abstract class PacketManager implements INetworkManagerPacketHandler {

	public static PacketManager				instance;
	protected HashMap<String, Class<?extends PacketManager>>	channelMap;

	@Override
	public void init() {
		instance = this;
		channelMap = new HashMap<>();
	}

	@Override
	public abstract void tick();

	@Override
	public void onPacketData(NetworkManager manager, INetworkManager network,
			Packet250CustomPayload packet, Player player) {

	}

	public void registerChannel(String channel, Class<?extends PacketManager> packetManager) {
		if (channelMap == null) { throw new RuntimeException(
				"You have to initalise the packet handler"); }

		channelMap.put(channel, packetManager);
	}
}
