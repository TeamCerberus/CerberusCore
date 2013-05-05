package teamcerberus.cerberuscore.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class CerberusLogger {
	private static Logger	logger	= Logger.getLogger("Cerberus");

	public static void init() {
		logger.setParent(FMLLog.getLogger());
	}

	public static void logInfo(String s) {
		logger.log(Level.INFO, s);
	}

	public static void logWarning(String s) {
		logger.log(Level.WARNING, s);
	}

	public static void logSevere(String s) {
		logger.log(Level.SEVERE, s);
	}

	public static Logger getLoggerInstance() {
		return logger;
	}
}
