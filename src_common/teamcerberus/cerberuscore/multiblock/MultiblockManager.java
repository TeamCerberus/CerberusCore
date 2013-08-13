package teamcerberus.cerberuscore.multiblock;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import teamcerberus.cerberuscore.render.CerbRenderManager;
import teamcerberus.cerberuscore.util.CerberusLogger;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MultiblockManager {
	public static boolean init;
	public static boolean clientInit;
	public static boolean enabled;
	public static Block blockMultiblock;
	public static int	blockMultiblockId;
	public static HashMap<String, Icon> textures;
	
	@SuppressWarnings("deprecation")
	public static void init(){
		if(!init){
			if(enabled){
				CerberusLogger.logInfo("Loading Multiblocks");
				blockMultiblock = new BlockMultiblock(blockMultiblockId);
				GameRegistry.registerBlock(blockMultiblock);
				GameRegistry.registerTileEntity(TileMultiblock.class, "tileMultiblock");
				LanguageRegistry.addName(blockMultiblock, "MULTIBLOCK - Should not be spawned!");
			}else
				CerberusLogger.logInfo("Multiblock's are disabled, Skipping...");
			init = true;
		}
	}
	
	
	
	public static void clientInit(){
		if(!clientInit){
			if(enabled){
				CerberusLogger.logInfo("Loading Multiblocks Client");
				CerbRenderManager.init();
				CerbRenderManager.addBlockRenderer(blockMultiblockId, 0, MBRenderer.instance);
				textures = new HashMap<String, Icon>();
			}else
				CerberusLogger.logInfo("Multiblock's are disabled, Skipping Client...");
			clientInit = true;
		}
	}
	
	
	public static void loadTexture(String textureName){
		
	}
	
}
