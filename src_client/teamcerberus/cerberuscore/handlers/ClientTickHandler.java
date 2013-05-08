package teamcerberus.cerberuscore.handlers;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import teamcerberus.cerberuscore.util.ClientUtil;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	@Override
	public String getLabel() {
		return "CerberusCoreTick";
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (ClientUtil.mc().theWorld != null && ClientUtil.mc().theWorld.playerEntities.size() > 0) {
			List<?> players = ClientUtil.mc().theWorld.playerEntities;

			for (int counter = 0; counter < players.size(); counter++) {
				if (players.get(counter) != null) {
					EntityPlayer player = (EntityPlayer) players
							.get(counter);
					if (ClientUtil.getCape(player.username) != null) {

						String oldCloak = player.cloakUrl;

						String newCloakUrl = ClientUtil
								.getCape(player.username);
						player.cloakUrl = newCloakUrl;

						if (player.cloakUrl != oldCloak) {
							ClientUtil.mc().renderEngine.obtainImageData(player.cloakUrl,
									new HDImageBufferDownload());
						}
					}
					
					if (ClientUtil.getSkin(player.username) != null) {

						String oldSkin = player.skinUrl;

						String newCloakUrl = ClientUtil
								.skins.get(player.username);
						player.skinUrl = newCloakUrl;

						if (player.skinUrl != oldSkin) {
							ClientUtil.mc().renderEngine.obtainImageData(player.skinUrl,
									new HDImageBufferDownload());
						}
					}
				}
			}
		}
	}
}
