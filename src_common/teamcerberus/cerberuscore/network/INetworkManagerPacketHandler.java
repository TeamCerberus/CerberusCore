package teamcerberus.cerberuscore.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

public interface INetworkManagerPacketHandler {
	public void onPacketData(NetworkManager manager, INetworkManager network, Packet250CustomPayload packet, Player player);
}
