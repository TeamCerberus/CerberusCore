package teamcerberus.cerberuscore;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import teamcerberus.cerberuscore.command.CerberusCommandManager;
import teamcerberus.cerberuscore.handlers.ClientTickHandler;
import teamcerberus.cerberuscore.util.CerberusLogger;
import teamcerberus.cerberuscore.util.ClientUtil;
import teamcerberus.cerberuscore.util.ServerUtil;

public class CerberusCore {
	public static final String	id				= "CerberusCore";
	public static final String	channelPrefix	= "CC_";
	public static final String	version			= "@VERSION@";

	protected static void preInit() {
		CerberusLogger.logInfo("Cerberus Core Loading...");
		CerberusLogger.logInfo("Version " + version);
		CerberusCommandManager.init();
		if (ClientUtil.isClient())
			ClientUtil.init();
		ServerUtil.init();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		CerberusLogger.logInfo("Loaded!");
	}
	
	protected static void init() {
	}

	protected static void preMCInit() {
		CerberusLogger.init();
		CerberusLogger.logInfo("PreMinecraft Initialization Complete!");
	}
}