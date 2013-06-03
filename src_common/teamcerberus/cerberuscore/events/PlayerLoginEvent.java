package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerLoginEvent extends CerberusPlayerEvent {

	public PlayerLoginEvent(EntityPlayer player) {
		super(player);
	}
}
