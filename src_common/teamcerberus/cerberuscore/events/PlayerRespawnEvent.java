package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerRespawnEvent extends CerberusPlayerEvent {

	public PlayerRespawnEvent(EntityPlayer player) {
		super(player);
	}
}
