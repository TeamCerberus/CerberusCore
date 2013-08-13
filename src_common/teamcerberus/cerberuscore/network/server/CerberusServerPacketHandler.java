package teamcerberus.cerberuscore.network.server;

import teamcerberus.cerberuscore.network.NetworkManager;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class CerberusServerPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		NetworkManager.instance.channelToHandler.get(packet.channel).onPacketData(Side.SERVER, manager, packet, player);
	}

}
