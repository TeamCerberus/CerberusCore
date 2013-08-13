package teamcerberus.cerberuscore.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
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

	public static void registerChannels(String[] strings,
			IPacketHandler handler, Side side) {
		for (String s : strings) {
			NetworkRegistry.instance().registerChannel(handler, s, side);
		}
	}
	
	public static NBTTagCompound readNBTTagCompound(
			DataInputStream par0DataInputStream) throws IOException {
		short var1 = par0DataInputStream.readShort();

		if (var1 < 0) {
			return null;
		} else {
			byte[] var2 = new byte[var1];
			par0DataInputStream.readFully(var2);
			return CompressedStreamTools.decompress(var2);
		}
	}

	public static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound,
			DataOutputStream par1DataOutputStream) throws IOException {
		if (par0NBTTagCompound == null) {
			par1DataOutputStream.writeShort(-1);
		} else {
			byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
			par1DataOutputStream.writeShort((short) var2.length);
			par1DataOutputStream.write(var2);
		}
	}
	
	public static NBTTagCompound readNBTTagCompound2(
			ByteArrayDataInput in) throws IOException {
		short var1 = in.readShort();

		if (var1 < 0) {
			return null;
		} else {
			byte[] var2 = new byte[var1];
			in.readFully(var2);
			return CompressedStreamTools.decompress(var2);
		}
	}

	public static void writeNBTTagCompound2(NBTTagCompound par0NBTTagCompound,
			ByteArrayDataOutput par1DataOutputStream) throws IOException {
		if (par0NBTTagCompound == null) {
			par1DataOutputStream.writeShort(-1);
		} else {
			byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
			par1DataOutputStream.writeShort((short) var2.length);
			par1DataOutputStream.write(var2);
		}
	}
}
