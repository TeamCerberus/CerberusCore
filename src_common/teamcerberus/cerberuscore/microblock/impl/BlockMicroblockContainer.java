package teamcerberus.cerberuscore.microblock.impl;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMicroblockContainer extends BlockMicroblockBase {
	public BlockMicroblockContainer(int id, Material mat) {
		super(id, mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileMicroblockContainer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
	}
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockId(par2, par3, par4);
        Block block = Block.blocksList[l];
        return block == null || block == this || block.isBlockReplaceable(par1World, par2, par3, par4);
    }
}
