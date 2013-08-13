package teamcerberus.cerberuscore.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import teamcerberus.cerberuscore.microblock.impl.BlockMicroblockBase;
import teamcerberus.cerberuscore.microblock.impl.ItemMicroblock;
import teamcerberus.cerberuscore.microblock.part.EnumPosition;
import teamcerberus.cerberuscore.microblock.part.PartCoordinates;
import teamcerberus.cerberuscore.util.NetworkUtil;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class MultiblockPacketHandler implements INetworkManagerPacketHandler {
	public static MultiblockPacketHandler instance;

	@Override
	public String getName() {
		return "Multiblock";
	}

	@Override
	public void init() {
		instance = this;
	}

	@Override
	public void tick() {

	}

	public void sendContainerDescription(int x, int y, int z, byte[] dataA) {
		NBTTagCompound data = new NBTTagCompound();
		data.setString("action", "containerDescription");
		data.setInteger("blockX", x);
		data.setInteger("blockY", y);
		data.setInteger("blockZ", z);
		data.setByteArray("data", dataA);
		sendNBTTToServer(data);
	}

	public void sendDigStart(PartCoordinates coord) {
		NBTTagCompound data = new NBTTagCompound();
		data.setString("action", "digStart");
		data.setInteger("blockX", coord.x);
		data.setInteger("blockY", coord.y);
		data.setInteger("blockZ", coord.z);
		data.setInteger("part", coord.part);
		data.setBoolean("isCSPart", coord.isCoverSystemPart);
		sendNBTTToServer(data);
	}

	public void sendPlaceMicroblock(int x, int y, int z, int posid,
			int sideClicked) {
		NBTTagCompound data = new NBTTagCompound();
		data.setString("action", "placeMicroblock");
		data.setInteger("blockX", x);
		data.setInteger("blockY", y);
		data.setInteger("blockZ", z);
		data.setInteger("posid", posid);
		data.setInteger("sideClicked", sideClicked);
		sendNBTTToServer(data);
	}

	public void sendNBTTToServer(NBTTagCompound data) {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(stream);
			NetworkUtil.writeNBTTagCompound(data, out);
			NetworkManager.sendPacketToServer(NetworkManager.createPacket(
					"multiblocks", stream));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPacketData(Side side, INetworkManager network,
			Packet250CustomPayload packet, Player player) {
		try {
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			NBTTagCompound data = NetworkUtil.readNBTTagCompound2(in);
			if (data.getString("action").equals("digStart")) {
				BlockMicroblockBase.setBreakingPart(
						(EntityPlayer) player,
						new PartCoordinates(data.getInteger("blockX"), data
								.getInteger("blockY"), data
								.getInteger("blockZ"), data.getInteger("part"),
								data.getBoolean("isCSPart")));
			} else if (data.getString("action").equals("placeMicroblock")) {
				EntityPlayer source = (EntityPlayer) player;
				int x = data.getInteger("blockX");
				int y = data.getInteger("blockY");
				int z = data.getInteger("blockZ");
				int posid = data.getInteger("posid");
				int sideClicked = data.getInteger("sideClicked");

				if (posid < 0 || posid >= EnumPosition.values().length) {
					// System.out.println("wrong position");
					return;
				}
				EnumPosition pos = EnumPosition.values()[posid];
				ItemStack h = source.getCurrentEquippedItem();
				if (h == null
						|| !(Item.itemsList[h.itemID] instanceof ItemMicroblock)) {
//					System.out.println("wrong item equipped");
					return;
				}
				ItemMicroblock i = (ItemMicroblock) Item.itemsList[h.itemID];

				if (i.placeInBlock(source.worldObj, x, y, z,
						pos, h, true, sideClicked)
						&& !source.capabilities.isCreativeMode) {
					h.stackSize--;
					if (h.stackSize == 0)
						source.destroyCurrentEquippedItem();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addNetworkChannel(ArrayList<String> list) {
		list.add("multiblocks");
	}

}
