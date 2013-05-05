package teamcerberus.cerberuscore.network.syncedfield;

import java.util.HashMap;

public class SyncedFile {
	private HashMap<String, SyncedFieldInstance>	syncedFields;
	private Object classInstance;
	private SyncedFieldManager manager;
	
	public void init(SyncedFieldManager manager) {
		this.manager = manager;
		syncedFields = new HashMap<String, SyncedFieldInstance>();
	}

	public void tick() {
		for (SyncedFieldInstance f : syncedFields.values()) {
			f.tick();
		}
	}
	
	public Object getClassInstance(){
		return classInstance;
	}
}
