package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		new PlayerLoginEvent(player).call();		
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		new PlayerLogoutEvent(player).call();		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		new PlayerChangedDimensionEvent(player).call();		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		new PlayerRespawnEvent(player).call();		
	}
}
