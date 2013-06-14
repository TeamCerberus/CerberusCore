package teamcerberus.cerberuscore.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public abstract class CerbBlockRenderer {
	public abstract void renderWorldBlock(RenderBlocks paramRenderBlocks,
			IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, Block block);

	public abstract void renderInvBlock(RenderBlocks paramRenderBlocks,
			int paramInt, Block block);
}
