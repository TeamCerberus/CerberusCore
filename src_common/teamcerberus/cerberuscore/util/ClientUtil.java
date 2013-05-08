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
		addCape("fzerox", "https://raw.github.com/TeamCerberus/TeamCapes/master/teamDefault.png");
		addCape("cazzar", "https://raw.github.com/TeamCerberus/TeamCapes/master/cazzar.png");
		addCape("Aroma1997", "https://raw.github.com/TeamCerberus/TeamCapes/master/teamDefault.png");
		addCape("DreadMagusX1","https://raw.github.com/TeamCerberus/TeamCapes/master/DreadMagusX1.png");
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
