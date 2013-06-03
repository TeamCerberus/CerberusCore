package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerLogoutEvent extends CerberusPlayerEvent {

	public PlayerLogoutEvent(EntityPlayer player) {
		super(player);
	}

}
