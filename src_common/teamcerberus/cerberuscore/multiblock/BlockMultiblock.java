package teamcerberus.cerberuscore.multiblock;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import teamcerberus.cerberuscore.render.CerbRenderManager;

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
	public TileEntity createNewTileEntity(World world) {
		return new TileMultiblock();
	}

}
