package teamcerberus.cerberuscore.network.syncedfield;

import java.util.HashMap;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import teamcerberus.cerberuscore.network.INetworkManagerPacketHandler;
import teamcerberus.cerberuscore.network.NetworkManager;
import cpw.mods.fml.common.network.Player;

public class SyncedFieldManager implements INetworkManagerPacketHandler {
	private static SyncedFieldManager	instance;
	private HashMap<String, SyncedFile>	syncedFiles;

	@Override
	public void init() {
		instance = this;

		syncedFiles = new HashMap<String, SyncedFile>();

	}

	@Override
	public void tick() {
		for (SyncedFile f : syncedFiles.values()) {
			f.tick();
		}
	}

	@Override
	public void onPacketData(NetworkManager manager, INetworkManager network,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub

	}

	public static SyncedFieldManager getInstance() {
		return instance;
	}
}
