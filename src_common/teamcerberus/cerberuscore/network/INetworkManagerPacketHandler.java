package teamcerberus.cerberuscore.network;

import java.util.ArrayList;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public interface INetworkManagerPacketHandler {
	public String getName();
	
	public void addNetworkChannel(ArrayList<String> list);
	
	public void init();

	public void tick();

	public void onPacketData(Side side, INetworkManager network,
			Packet250CustomPayload packet, Player player);
}
