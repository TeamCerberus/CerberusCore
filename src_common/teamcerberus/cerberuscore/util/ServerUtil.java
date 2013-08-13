package teamcerberus.cerberuscore.util;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import teamcerberus.cerberuscore.events.PlayerTracker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerUtil {
	public static MinecraftServer getServerInstance() {
		return FMLCommonHandler.instance().getMinecraftServerInstance();
	}

	public static File getMinecraftFolder() {
		if (NetworkUtil.isDedicatedServer()) {
			return ServerUtil.getServerInstance().getFile("");
		} else {
			return Minecraft.getMinecraft().mcDataDir;
		}
	}

	public static File getWorldFolder() {
		String world = (NetworkUtil.isDedicatedServer() ? "" : "saves/")
				+ getWorldName();
		return new File(getMinecraftFolder(), world);
	}

	public static String getWorldName() {
		return ServerUtil.getServerInstance().getFolderName();
	}
	
	public static void init() {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}
}
