package teamcerberus.cerberuscore.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CerbRenderManager {
	public static HashMap<String, CerbBlockRenderer>	blockRenders	= new HashMap<String, CerbBlockRenderer>();
	public static int									customBlockModel;
	public static boolean								hasInit;
	
	public static CerbBlockRenderer getRenderer(int blockID, int md) {
		if (!hasInit) init();
		return blockRenders.get(blockID + "#" + md);
	}

	public static void addBlockRenderer(int blockID, int md,
			CerbBlockRenderer renderer) {
		if (!hasInit) init();
		blockRenders.put(blockID + "#" + md, renderer);
	}

	public static void init() {
		if (!hasInit) {
			hasInit = true;
			customBlockModel = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(customBlockModel,
					new RenderHandler());
		}
	}

	
	
	public static void tickTextures() {
		if(!hasInit)
			init();
	}

	public static class RenderHandler implements ISimpleBlockRenderingHandler {
		@Override
		public void renderInventoryBlock(Block block, int metadata,
				int modelID, RenderBlocks renderer) {
			if (modelID != customBlockModel) { return; }
			CerbBlockRenderer rcb = getRenderer(block.blockID, metadata);

			if (rcb == null) {
				System.out.printf(
						"Bad Render at %d:%d\n",
						new Object[] { Integer.valueOf(block.blockID),
								Integer.valueOf(metadata) });

				return;
			}
			rcb.renderInvBlock(renderer, metadata, block);
		}

		@Override
		public boolean renderWorldBlock(IBlockAccess world, int x, int y,
				int z, Block block, int modelId, RenderBlocks renderer) {
			if (modelId != customBlockModel) { return false; }
			int md = world.getBlockMetadata(x, y, z);
			CerbBlockRenderer rcb = getRenderer(block.blockID, md);

			if (rcb == null) {
				System.out.printf("Bad Render at %d:%d\n", new Object[] {
						Integer.valueOf(block.blockID), Integer.valueOf(md) });

				return true;
			}
			rcb.renderWorldBlock(renderer, world, x, y, z, md, block);
			return true;
		}

		public boolean shouldRender3DInInventory() {
			return true;
		}

		public int getRenderId() {
			return customBlockModel;
		}
	}
}
