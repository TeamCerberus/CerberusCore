package teamcerberus.cerberuscore.network;

import java.util.ArrayList;
import java.util.Arrays;

import cpw.mods.fml.relauncher.Side;

import teamcerberus.cerberuscore.CerberusCore;
import teamcerberus.cerberuscore.network.client.CerberusClientPacketHandler;
import teamcerberus.cerberuscore.network.server.CerberusServerPacketHandler;
import teamcerberus.cerberuscore.util.MiscUtil;
import teamcerberus.cerberuscore.util.NetworkUtil;

public class NetworkManager {
	private static NetworkManager		instance;
	private CerberusServerPacketHandler	serverPacketHandler;
	private CerberusClientPacketHandler	clientPacketHandler;

	private void setup() {
		serverPacketHandler = new CerberusServerPacketHandler();
		clientPacketHandler = new CerberusClientPacketHandler();

		String[] channels = new String[] {"syncedField"};

		String[] prefixedChannels = MiscUtil.addPrefixToArray(channels,
				CerberusCore.channelPrefix);
		NetworkUtil.registerChannels(prefixedChannels, serverPacketHandler,
				Side.SERVER);
		NetworkUtil.registerChannels(prefixedChannels, clientPacketHandler,
				Side.CLIENT);
	}

	public static void init() {
		instance = new NetworkManager();
		instance.setup();
	}

	public NetworkManager getInstance() {
		return instance;
	}
}
