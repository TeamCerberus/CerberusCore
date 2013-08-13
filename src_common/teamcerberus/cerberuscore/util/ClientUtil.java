package teamcerberus.cerberuscore.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.w3c.dom.Node;

import teamcerberus.cerberuscore.xml.CerberusXMLParser;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientUtil {
	private static HashMap<String, String> capes;
	public static HashMap<String, String> skins;

	public static void init() {
		capes = new HashMap<String, String>();
		skins = new HashMap<String, String>();

		try {
			CerberusXMLParser xml = new CerberusXMLParser(
					new URL(
							"https://raw.github.com/TeamCerberus/TeamCapes/master/info.xml")
							.openStream());
			for (Entry<String, Node> entry : xml.getAll().entrySet()) {
				// System.out.println(entry.getKey() + ":" +
				// xml.getNodeValue(entry.getValue()));
				if (entry.getKey().split("\\.").length != 2)
					continue;

				String username = entry.getKey().split("\\.")[0];
				String part = entry.getKey().split("\\.")[1];
				if (part.equalsIgnoreCase("cape")
						&& !entry.getValue().getTextContent().isEmpty())
					setCape(username, entry.getValue().getTextContent());
				else if (part.equalsIgnoreCase("skin")
						&& !entry.getValue().getTextContent().isEmpty())
					setSkin(username, entry.getValue().getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// setCape("fzerox",
		// "https://raw.github.com/TeamCerberus/TeamCapes/master/teamDefault.png");
		// setCape("cazzar",
		// "https://raw.github.com/TeamCerberus/TeamCapes/master/cazzar.png");
		// setCape("Aroma1997",
		// "https://raw.github.com/TeamCerberus/TeamCapes/master/Aroma1997.png");
		// setCape("DreadMagusX1","https://raw.github.com/TeamCerberus/TeamCapes/master/DreadMagusX1.png");
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

	public static MovingObjectPosition retraceBlock(World world,
			EntityLiving ent, int i, int j, int k) {
		Vec3 org = Vec3.createVectorHelper(ent.posX, ent.posY + 1.62D
				- ent.yOffset, ent.posZ);

		Vec3 vec = ent.getLook(1.0F);
		Vec3 end = org.addVector(vec.xCoord * 5.0D, vec.yCoord * 5.0D,
				vec.zCoord * 5.0D);

		Block bl = Block.blocksList[world.getBlockId(i, j, k)];
		if (bl == null)
			return null;
		return bl.collisionRayTrace(world, i, j, k, org, end);
	}
}
