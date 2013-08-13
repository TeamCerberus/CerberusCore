package teamcerberus.cerberuscore;

import teamcerberus.cerberuscore.util.CerberusLogger;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class CerberusCoreSubcriber {
	public static Icon testIcon;
	
	@ForgeSubscribe
	public void preTextureStitch(TextureStitchEvent.Pre ev){
		
		if(ev.map.textureType == 0){
			CerberusLogger.logInfo("Registering texture");
			testIcon = ev.map.registerIcon("multiblocks/example");
		}
		
	}
}
