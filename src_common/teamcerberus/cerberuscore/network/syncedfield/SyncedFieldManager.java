package teamcerberus.cerberuscore.network.syncedfield;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import teamcerberus.cerberuscore.network.INetworkManagerPacketHandler;
import teamcerberus.cerberuscore.network.NetworkManager;
import teamcerberus.cerberuscore.util.MiscUtil;
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
		for (SyncedFile f : syncedFiles.values())
			f.tick();
	}

	public void registerSyncedFile(Object instance, Class classType) {
		if (!(instance instanceof ISyncedFile)) throw new RuntimeException(
				"Synced files must implement ISyncedFile @ " + instance);
		ISyncedFile s = (ISyncedFile) instance;
		SyncedFile file = new SyncedFile();
		file.init(s, instance);
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			try {
				SyncedField annotation = field.getAnnotation(SyncedField.class);
				if (annotation == null) continue;
				SyncedFieldInstance syncedFieldInstance = new SyncedFieldInstance(
						file, field, field.getName());
				syncedFieldInstance.setValue(field.get(instance));
				System.out.println("Synced Field Found: " + field.getName()
						+ " with " + field.get(instance));
			} catch (Exception e) {}
		}
	}

	public void syncField(String ident, String name, Object value) {
//		NetworkUtil.isServer()
		ByteArrayOutputStream packet = new ByteArrayOutputStream(540);
		DataOutputStream out = new DataOutputStream(packet);
		try {
			out.writeUTF(ident);
			out.writeUTF(name);
			byte[] bytes = MiscUtil.objectToBytes(value);
			out.writeInt(bytes.length);
			
		} catch (Exception e) {
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

	@Override
	public String getName() {
		return "syncedField";
	}
}
