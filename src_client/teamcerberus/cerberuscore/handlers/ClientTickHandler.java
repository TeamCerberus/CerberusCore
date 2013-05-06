package teamcerberus.cerberuscore.handlers;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import teamcerberus.cerberuscore.util.ClientUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {
	
	private static final Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0))
	    {
	      List<?> players = mc.theWorld.playerEntities;

	      for (int counter = 0; counter < players.size(); counter++)
	      {
	        if (players.get(counter) != null) {
	          EntityPlayer thePlayer = (EntityPlayer)players.get(counter);
	          if (ClientUtil.getCape(thePlayer.username) == null) continue;
	          
	          String oldCloak = thePlayer.cloakUrl;

	          String newCloakUrl = ClientUtil.getCape(thePlayer.username);
	          thePlayer.cloakUrl = newCloakUrl;

	          if (thePlayer.cloakUrl != oldCloak)
	            mc.renderEngine.obtainImageData(thePlayer.cloakUrl, new ImageBufferDownload());
	        }
	      }
	    }
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "CerberusCoreTick";
	}
}
