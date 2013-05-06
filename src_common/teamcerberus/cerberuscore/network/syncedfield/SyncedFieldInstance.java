package teamcerberus.cerberuscore.network.syncedfield;

import java.lang.reflect.Field;

public class SyncedFieldInstance {
	private Object		value;
	private Field		field;
	private SyncedFile	file;
	private String		name;

	public SyncedFieldInstance(SyncedFile file, Field field, String name) {
		this.file = file;
		this.field = field;
		this.name = name;
	}

	public void tick() {
		try {
			Object current = field.get(file.getClassInstance());
			if(!current.equals(value)){
				value = current;
				
				SyncedFieldManager.getInstance().syncField(file.getIdent(), name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setValue(Object o) {
		value = o;
	}

	public Object getValue() {
		return value;
	}

}
