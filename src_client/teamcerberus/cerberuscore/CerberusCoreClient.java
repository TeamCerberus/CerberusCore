package teamcerberus.cerberuscore;

import teamcerberus.cerberuscore.handlers.ClientTickHandler;
import teamcerberus.cerberuscore.microblock.MultiblockManager;
import teamcerberus.cerberuscore.util.ClientUtil;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CerberusCoreClient {
	protected static void preInit(FMLPreInitializationEvent ev) {
		ClientUtil.init();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		MultiblockManager.clientInit();
	}
	
	protected static void init() {
	}
}
