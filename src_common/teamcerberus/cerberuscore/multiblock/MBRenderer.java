package teamcerberus.cerberuscore.multiblock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import teamcerberus.cerberuscore.render.CerbBlockRenderer;

public class MBRenderer extends CerbBlockRenderer{
	public static MBRenderer instance = new MBRenderer();
	
	@Override
	public void renderWorldBlock(RenderBlocks renderblocks,
			IBlockAccess paramIBlockAccess, int x, int y,
			int z, int meta, Block block) {
		TileMultiblock tile = (TileMultiblock) paramIBlockAccess.getBlockTileEntity(x, y, z);
		for(MBBox box : tile.getBoxes()){
			block.setBlockBounds(box.getX(), box.getY(), box.getZ(), box.getWidth(), box.getHeight(), box.getDepth());
			renderblocks.setRenderBoundsFromBlock(block);
			renderblocks.renderStandardBlock(block, x, y, z);
		}
		block.setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
	}

	@Override
	public void renderInvBlock(RenderBlocks paramRenderBlocks, int paramInt, Block block) {
		// TODO Auto-generated method stub
		
	}

}
