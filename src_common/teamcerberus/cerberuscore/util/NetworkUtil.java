package teamcerberus.cerberuscore.util;

import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class NetworkUtil {
	public static Side getSide() {
		return FMLCommonHandler.instance().getEffectiveSide();
	}

	public static boolean isServer() {
		return getSide().isServer();
	}

	public static boolean isClient() {
		return getSide().isClient();
	}

	public static boolean isClient(Side s) {
		return s.isClient();
	}

	public static boolean isServer(Side s) {
		return s.isServer();
	}

	public static boolean isDedicatedServer() {
		return isServer() && ServerUtil.getServerInstance().isDedicatedServer();
	}
}
