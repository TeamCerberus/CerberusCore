package teamcerberus.cerberuscore.network.syncedfield;

import java.lang.reflect.Field;

public class SyncedFieldInstance {
	private Object		instance;
	private SyncedField	anotation;
	private Field		field;
	private SyncedFile	file;

	public SyncedFieldInstance(SyncedFile file) {
		this.file = file;
	}

	public void tick() {
		try {
			Object current = field.get(file.getClassInstance());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setValue(Object o) {
		instance = o;
	}

}
