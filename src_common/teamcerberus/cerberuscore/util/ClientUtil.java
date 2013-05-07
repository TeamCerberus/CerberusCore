package teamcerberus.cerberuscore.util;

import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientUtil {
	private static HashMap<String, String>	capes;

	public static void init() {
		capes = new HashMap<String, String>();
		addCape("fzerox", "http://n3network.co.uk/newmancape.png");
		addCape("cazzar", "http://direct.cazzar.net/cazzar.png");
		addCape("Aroma1997", "http://n3network.co.uk/newmancape.png");
		addCape("DreadMagusX1","http://i.imgur.com/aiOAn8C.png");
	}

	public static void addCape(String playerName, String capeURL) {
		capes.put(playerName.toLowerCase(), capeURL);
	}

	public static String getCape(String username) {
		return capes.get(username.toLowerCase());
	}

	public static Minecraft mc() {
		return Minecraft.getMinecraft();
	}

	public static World getWorld() {
		return mc().theWorld;
	}

	public static EntityPlayer getPlayer(String playername) {
		return playername == mc().thePlayer.username || playername == null ? mc().thePlayer
				: null;
	}

	public static boolean isClient(World world) {
		return world instanceof WorldClient;
	}

	public static boolean inWorld() {
		return mc().getNetHandler() != null;
	}

	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}
}
