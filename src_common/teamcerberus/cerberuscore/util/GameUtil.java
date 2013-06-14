package teamcerberus.cerberuscore.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GameUtil {
	public static void dropItem(World world, int i, int j, int k, ItemStack ist) {
		if (isWorldClient(world)) return;
		double d = 0.7D;
		double x = world.rand.nextFloat() * d + (1.0D - d) * 0.5D;
		double y = world.rand.nextFloat() * d + (1.0D - d) * 0.5D;
		double z = world.rand.nextFloat() * d + (1.0D - d) * 0.5D;
		EntityItem item = new EntityItem(world, i + x, j + y, k + z, ist);
		item.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(item);
	}

	public static boolean isWorldClient(World world) {
		return world.isRemote;
	}

	public static void markBlockDirty(World world, int i, int j, int k) {
		if (world.blockExists(i, j, k)) world.getChunkFromBlockCoords(i, k)
				.setChunkModified();
	}

	public static void updateIndirectNeighbors(World w, int i, int j, int k,
			int bid) {
		if (isWorldClient(w)) return;
		for (int a = -3; a <= 3; a++)
			for (int b = -3; b <= 3; b++)
				for (int c = -3; c <= 3; c++) {
					int md = a < 0 ? -a : a;
					md += (b < 0 ? -b : b);
					md += (c < 0 ? -c : c);
					if (md <= 3) notifyBlock(w, i + a, j + b, k + c, bid);
				}
	}

	public static void notifyBlock(World world, int i, int j, int k, int l) {
		Block block = Block.blocksList[world.getBlockId(i, j, k)];
		if (block != null) block.onNeighborBlockChange(world, i, j, k, l);
	}

	public static Object getTileEntity(IBlockAccess iba, int i, int j, int k,
			Class cl) {
		TileEntity tr = iba.getBlockTileEntity(i, j, k);
		if (!cl.isInstance(tr)) return null;
		return tr;
	}

	public static MovingObjectPosition retraceBlock(World world,
			EntityLiving ent, int i, int j, int k) {
		Vec3 org = Vec3.createVectorHelper(ent.posX, ent.posY + 1.62D
				- ent.yOffset, ent.posZ);

		Vec3 vec = ent.getLook(1.0F);
		Vec3 end = org.addVector(vec.xCoord * 5.0D, vec.yCoord * 5.0D,
				vec.zCoord * 5.0D);

		Block bl = Block.blocksList[world.getBlockId(i, j, k)];
		if (bl == null) return null;
		return bl.collisionRayTrace(world, i, j, k, org, end);
	}

}
