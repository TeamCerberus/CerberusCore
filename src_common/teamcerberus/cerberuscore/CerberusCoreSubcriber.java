package teamcerberus.cerberuscore;

import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class CerberusCoreSubcriber {
	public static Icon testIcon;
	
	@ForgeSubscribe
	public void preTextureStitch(TextureStitchEvent.Pre ev){
		
		if(ev.map.textureType == 0){
			System.out.println("Registering texture");
			testIcon = ev.map.registerIcon("multiblocks/example");
		}
		
	}
}
