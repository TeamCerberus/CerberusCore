package teamcerberus.cerberuscore.network.syncedfield;

import java.util.HashMap;

public class SyncedFile {
	private HashMap<String, SyncedFieldInstance>	syncedFields;
	private Object									classInstance;
	private ISyncedFile								classInterface;
	private String ident;

	public void init(ISyncedFile classInterface, Object classInstance) {
		syncedFields = new HashMap<String, SyncedFieldInstance>();
		this.classInstance = classInstance;
		this.classInterface = classInterface;
		ident = classInterface.instanceIdent();
	}

	public void tick() {
		for (SyncedFieldInstance f : syncedFields.values()) {
			f.tick();
		}
	}

	public Object getClassInstance() {
		return classInstance;
	}
	
	public String getIdent(){
		return ident;
	}
	
	public ISyncedFile getInterface(){
		return classInterface;
	}
}
