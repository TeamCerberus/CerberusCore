package teamcerberus.cerberuscore.util;

import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientUtil {
	private static HashMap<String, String>	capes;
	public static HashMap<String, String>	skins;

	public static void init() {
		capes = new HashMap<String, String>();
		skins = new HashMap<String, String>();
		setCape("fzerox", "https://raw.github.com/TeamCerberus/TeamCapes/master/teamDefault.png");
		setCape("cazzar", "https://raw.github.com/TeamCerberus/TeamCapes/master/cazzar.png");
		setCape("Aroma1997", "https://raw.github.com/TeamCerberus/TeamCapes/master/teamDefault.png");
		setCape("DreadMagusX1","https://raw.github.com/TeamCerberus/TeamCapes/master/DreadMagusX1.png");
	}

	@Deprecated
	/**
	 * Deprecated use setCape instead
	 * @param playerName the player name to set the cape for
	 * @param capeURL the cape URL
	 */
	public static void addCape(String playerName, String capeURL) {
		setCape(playerName.toLowerCase(), capeURL);
	}
	public static void setCape(String playerName, String capeURL) {
		capes.put(playerName.toLowerCase(), capeURL);
	}

	public static String getCape(String username) {
		return capes.get(username.toLowerCase());
	}

	public static void setSkin(String playerName, String skinURL) {
		skins.put(playerName.toLowerCase(), skinURL);
	}

	public static String getSkin(String username) {
		return skins.get(username.toLowerCase());
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
