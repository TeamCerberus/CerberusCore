package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerChangedDimensionEvent extends CerberusPlayerEvent {

	public PlayerChangedDimensionEvent(EntityPlayer player) {
		super(player);
	}
}
