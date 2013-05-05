package teamcerberus.cerberuscore.network;

import java.util.ArrayList;
import java.util.Arrays;

import cpw.mods.fml.relauncher.Side;

import teamcerberus.cerberuscore.network.client.CerberusClientPacketHandler;
import teamcerberus.cerberuscore.network.server.CerberusServerPacketHandler;
import teamcerberus.cerberuscore.util.NetworkUtil;

public class NetworkManager {
	private static NetworkManager	instance;
	private CerberusServerPacketHandler serverPacketHandler;
	private CerberusClientPacketHandler clientPacketHandler;
	
	private void setup() {
		serverPacketHandler = new CerberusServerPacketHandler();
		clientPacketHandler = new CerberusClientPacketHandler();
		
		ArrayList<String> channels = new ArrayList<String>();
		
		NetworkUtil.registerChannels(new String[]{"bob"}, serverPacketHandler, Side.SERVER);
		NetworkUtil.registerChannels(new String[]{"bob"}, clientPacketHandler, Side.CLIENT);
	}

	public static void init() {
		instance = new NetworkManager();
		instance.setup();
	}

	public NetworkManager getInstance() {
		return instance;
	}
}
