package teamcerberus.cerberuscore.multiblock;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import teamcerberus.cerberuscore.render.CerbRenderManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultiblock extends BlockContainer{

	public BlockMultiblock(int par1) {
		super(par1, Material.rock);
		setUnlocalizedName("blockMultiblock");
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public int getRenderType() {
		return CerbRenderManager.customBlockModel;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z,
			ForgeDirection side) {
		return getTileMultiblock(world, x, y, z).isSolid(side);
	}
	
	public TileMultiblock getTileMultiblock(World world, int x, int y, int z){
		return ((TileMultiblock)world.getBlockTileEntity(x, y, z));
	}
	
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3,
			int par4, AxisAlignedBB par5AxisAlignedBB, List par6List,
			Entity par7Entity) {
		for(MBBox box : getTileMultiblock(par1World, par2, par3, par4).getBoxes()){
			setBlockBounds(box.getX(), box.getY(), box.getZ(), box.getWidth(), box.getHeight(), box.getDepth());
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB,
					par6List, par7Entity);
		}
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		// TODO Auto-generated method stub
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction) {
		MBRaytraceResult raytraceResult = doRayTrace(world, x, y, z, origin, direction);

		if (raytraceResult == null) {
			return null;
		} else {
			return raytraceResult.movingObjectPosition;
		}
	}

	public MBRaytraceResult doRayTrace(World world, int x, int y, int z, EntityPlayer entityPlayer) {
		double pitch = Math.toRadians(entityPlayer.rotationPitch);
		double yaw = Math.toRadians(entityPlayer.rotationYaw);

        double dirX = -Math.sin(yaw) * Math.cos(pitch);
        double dirY = -Math.sin(pitch);
        double dirZ = Math.cos(yaw) * Math.cos(pitch);

        double reachDistance = 5;

        if (entityPlayer instanceof EntityPlayerMP) {
        	reachDistance = ((EntityPlayerMP) entityPlayer).theItemInWorldManager.getBlockReachDistance();
        }

		Vec3 origin = Vec3.fakePool.getVecFromPool(entityPlayer.posX, entityPlayer.posY + 1.62 - entityPlayer.yOffset, entityPlayer.posZ);
		Vec3 direction = origin.addVector(dirX * reachDistance, dirY * reachDistance, dirZ * reachDistance);

		return doRayTrace(world, x, y, z, origin, direction);
	}

	public MBRaytraceResult doRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction) {
		TileMultiblock tile = (TileMultiblock) world.getBlockTileEntity(x, y, z);
		if (tile == null) 
			return null;
		
		MBBox[] boxes = new MBBox[tile.getBoxes().size()];
		MovingObjectPosition[] hits = new MovingObjectPosition[tile.getBoxes().size()];
		
		for(int i = 0; i < tile.getBoxes().size(); i++){
			MBBox box = tile.getBoxes().get(i);
			setBlockBounds(box.getX(), box.getY(), box.getZ(), box.getWidth(), box.getHeight(), box.getDepth());
			boxes[i] = box;
			hits[i] = super.collisionRayTrace(world, x, y, z, origin, direction);
		}
		

		double minLengthSquared = Double.POSITIVE_INFINITY;
		int minIndex = -1;

		for (int i = 0; i < hits.length; i++) {
			MovingObjectPosition hit = hits[i];
			if (hit == null) continue;

			double lengthSquared = hit.hitVec.squareDistanceTo(origin);

			if (lengthSquared < minLengthSquared) {
				minLengthSquared = lengthSquared;
				minIndex = i;
			}
		}

		setBlockBounds(0, 0, 0, 1, 1, 1);

		if (minIndex == -1) {
			return null;
		} else {
			return new MBRaytraceResult(boxes[minIndex], hits[minIndex]);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileMultiblock();
	}

}
