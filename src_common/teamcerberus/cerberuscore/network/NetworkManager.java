package teamcerberus.cerberuscore.network;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.network.packet.Packet250CustomPayload;

import teamcerberus.cerberuscore.CerberusCore;
import teamcerberus.cerberuscore.network.client.CerberusClientPacketHandler;
import teamcerberus.cerberuscore.network.server.CerberusServerPacketHandler;
import teamcerberus.cerberuscore.network.syncedfield.SyncedFieldManager;
import teamcerberus.cerberuscore.util.MiscUtil;
import teamcerberus.cerberuscore.util.NetworkUtil;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public class NetworkManager {
	public static NetworkManager							instance;
	private CerberusServerPacketHandler						serverPacketHandler;
	private CerberusClientPacketHandler						clientPacketHandler;
	private HashMap<String, INetworkManagerPacketHandler>	packetHandlers;
	public HashMap<String, INetworkManagerPacketHandler>	channelToHandler;

	private void setup() {
		serverPacketHandler = new CerberusServerPacketHandler();
		clientPacketHandler = new CerberusClientPacketHandler();
		packetHandlers = new HashMap<String, INetworkManagerPacketHandler>();
		channelToHandler = new HashMap<String, INetworkManagerPacketHandler>();
		
		registerNetworkHandler(new SyncedFieldManager());
		registerNetworkHandler(new MultiblockPacketHandler());
		
		//Channel  names have a limit of 16 chars, they will have CC_ added to them, so 16-3
		for(Entry<String, INetworkManagerPacketHandler> entry : packetHandlers.entrySet()){
			ArrayList<String> chanlist = new ArrayList<String>();
			entry.getValue().addNetworkChannel(chanlist);
			for(String s : chanlist){
				channelToHandler.put(CerberusCore.channelPrefix+s, entry.getValue());
				NetworkRegistry.instance().registerChannel(serverPacketHandler, CerberusCore.channelPrefix+s, Side.SERVER);
				NetworkRegistry.instance().registerChannel(clientPacketHandler, CerberusCore.channelPrefix+s, Side.CLIENT);
			}
		}
	}
	
	private void registerNetworkHandler(INetworkManagerPacketHandler handler){
		packetHandlers.put(handler.getName(), handler);
		handler.init();
	}
	
	public static void init() {
		instance = new NetworkManager();
		instance.setup();
	}

	public NetworkManager getInstance() {
		return instance;
	}
	
	public static Packet250CustomPayload createPacket(String channel, ByteArrayOutputStream outbytes) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		byte[] bytes = outbytes.toByteArray();
		packet.channel = CerberusCore.channelPrefix+channel;
		packet.data = bytes;
		packet.length = packet.data.length;
		return packet;
	}
	
	public static void sendPacketToServer(Packet250CustomPayload packet) {
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	public static void sendPacketToAllClients(Packet250CustomPayload packet) {
		PacketDispatcher.sendPacketToAllPlayers(packet);
	}
}
