package teamcerberus.cerberuscore.util;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class ServerUtil {
	public static MinecraftServer getServerInstance(){
		return FMLCommonHandler.instance().getMinecraftServerInstance();
	}
	
	public static File getMinecraftFolder(){
		if(NetworkUtil.isDedicatedServer())
			return ServerUtil.getServerInstance().getFile("");
		else
			return Minecraft.getMinecraftDir();
	}
	
	public static File getWorldFolder(){
		String world = (NetworkUtil.isDedicatedServer() ? "" : "saves/")+getWorldName();
		return new File(getMinecraftFolder(), world);
	}
	
	public static String getWorldName(){
		return ServerUtil.getServerInstance().getFolderName();
	}
}
