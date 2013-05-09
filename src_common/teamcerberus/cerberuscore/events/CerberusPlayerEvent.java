package teamcerberus.cerberuscore.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class CerberusPlayerEvent extends PlayerEvent {
	static boolean eventsRegistered = false;
	
	public CerberusPlayerEvent(EntityPlayer player) {
		super(player);
	}
	
	protected void call(){
		MinecraftForge.EVENT_BUS.post(this);
	}
}
