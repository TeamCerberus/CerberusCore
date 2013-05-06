package teamcerberus.cerberuscore;

import teamcerberus.cerberuscore.command.CerberusCommandManager;
import teamcerberus.cerberuscore.network.NetworkManager;
import teamcerberus.cerberuscore.util.CerberusLogger;

public class CerberusCore {
	public static final String	id				= "CerberusCore";
	public static final String	channelPrefix	= "CC_";
	public static final String	version			= "@VERSION@";

	protected static void preInit() {
		CerberusLogger.logInfo("Cerberus Core Loading...");
		CerberusLogger.logInfo("Version " + version);
		CerberusCommandManager.init();
		NetworkManager.init();
		CerberusLogger.logInfo("Loaded!");
	}
	
	protected static void init() {
	}

	protected static void preMCInit() {
		CerberusLogger.init();
		CerberusLogger.logInfo("PreMinecraft Initialization Complete!");
	}
}