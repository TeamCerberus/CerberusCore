package teamcerberus.cerberuscore;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import teamcerberus.cerberuscore.util.CerberusLogger;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class CerberusCoreAccessTransformer extends AccessTransformer {
	private static CerberusCoreAccessTransformer	instance;
	private static List<String>						mapFileList	= new LinkedList<String>();

	public CerberusCoreAccessTransformer() throws IOException {
		super();
		instance = this;
		for (String file : mapFileList) {
			readMapFile(file);
		}

		mapFileList = null;
	}

	public static void addTransformerMap(String mapFile) {
		if (instance == null) {
			mapFileList.add(mapFile);
		} else {
			instance.readMapFile(mapFile);
		}
	}

	private void readMapFile(String mapFile) {
		CerberusLogger.logInfo("Adding Accesstransformer map: " + mapFile);
		try {
			Method parentMapFile = AccessTransformer.class.getDeclaredMethod(
					"readMapFile", String.class);
			parentMapFile.setAccessible(true);
			parentMapFile.invoke(this, mapFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
