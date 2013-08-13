package teamcerberus.cerberuscore.microblock;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import teamcerberus.cerberuscore.microblock.impl.BlockMicroblockBase;
import teamcerberus.cerberuscore.microblock.impl.BlockMicroblockContainer;
import teamcerberus.cerberuscore.microblock.impl.IMicroblockSupporterTile;
import teamcerberus.cerberuscore.microblock.impl.ItemMicroblock;
import teamcerberus.cerberuscore.microblock.impl.ItemSaw;
import teamcerberus.cerberuscore.microblock.impl.TileMicroblockContainer;
import teamcerberus.cerberuscore.microblock.part.Part;
import teamcerberus.cerberuscore.microblock.part.PartCoordinates;
import teamcerberus.cerberuscore.render.CerbRenderManager;
import teamcerberus.cerberuscore.util.CerberusLogger;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MultiblockManager {
	public static boolean init;
	public static boolean clientInit;
	public static boolean enabled;
	public static Block microblockContainerBlock;
	public static int	blockMultiblockId;
	public static int renderType;
	public static HashMap<String, Icon> textures;
	
	public static void init(){
		if(!init){
			if(enabled){
				CerberusLogger.logInfo("Loading Multiblocks");
				microblockContainerBlock = new BlockMicroblockContainer(blockMultiblockId, Material.rock).setCreativeTab(CreativeTabs.tabRedstone);
				GameRegistry.registerBlock(microblockContainerBlock, ItemMicroblock.class, "MicroblockContainer");
				
				Block t = new BlockMicroblockContainer(blockMultiblockId+2, Material.rock).setCreativeTab(CreativeTabs.tabRedstone);
				GameRegistry.registerBlock(t, "los");
				
				GameRegistry.registerTileEntity(TileMicroblockContainer.class, "microblock");
				LanguageRegistry.addName(microblockContainerBlock, "MULTIBLOCK - Should not be spawned!");
				GameRegistry.registerItem(new ItemSaw(5011).setCreativeTab(CreativeTabs.tabRedstone), "multiblock");
				MicroblockSystem.registerManualParts(1, Block.stone);
			}else
				CerberusLogger.logInfo("Multiblock's are disabled, Skipping...");
			init = true;
			
		}
	}
	
	
	
	public static void clientInit(){
		if(!clientInit){
			if(enabled){
				CerberusLogger.logInfo("Loading Multiblocks Client");
				CerbRenderManager.init();
				MultiblockManager.renderType = RenderingRegistry.getNextAvailableRenderId();
				MinecraftForgeClient.registerItemRenderer(microblockContainerBlock.blockID, new MicroblockItemRenderer());
				MinecraftForge.EVENT_BUS.register(new MultipartHighlightHandler());
				MinecraftForge.EVENT_BUS.register(new MicroblockPlacementHighlightHandler());
				
				RenderingRegistry.registerBlockHandler(new ISimpleBlockRenderingHandler() {
					@Override
					public boolean shouldRender3DInInventory() {
						return true;
					}
					
					@Override
					public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks render) {
						TileEntity te = world.getBlockTileEntity(x, y, z);
						
						if(!(te instanceof IMicroblockSupporterTile)) {
							BlockMicroblockBase.renderBlockStatic(render, block, x, y, z);
							return true;
						}
						
						IMicroblockSupporterTile imt = (IMicroblockSupporterTile)te;
						
						MicroblockCoverSystem ci = imt.getCoverSystem();
						
						boolean damageLayer = render.overrideBlockTexture != null;
						
						if(!damageLayer) {
							imt.render(render);
						
							if(ci != null)
								ci.render(render);
						}
						else {
							for(Map.Entry<EntityPlayer, PartCoordinates> breaking : BlockMicroblockBase.getBreakingParts()) {
								if(!breaking.getKey().worldObj.isRemote)
									continue;
								
								PartCoordinates pc = breaking.getValue();
								if(pc.x == x && pc.y == y && pc.z == z) {
									if(!pc.isCoverSystemPart)
										imt.renderPart(render, pc.part);
									else if(ci != null)
										ci.renderPart(render, pc.part);
								}
							}
						}
						return true;
					}
					
					@Override
					public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
						MultiblockManager.RenderInvBlock(renderer, block, metadata);
					}
					
					@Override
					public int getRenderId() {
						return MultiblockManager.renderType;
					}
				});
				textures = new HashMap<String, Icon>();
			}else
				CerberusLogger.logInfo("Multiblock's are disabled, Skipping Client...");
			clientInit = true;
		}
	}
	
	public static void RenderWorldBlock(RenderBlocks render, IBlockAccess world, int x, int y, int z, Block block) {
		
	}
	
	public static void RenderInvBlock(RenderBlocks render, Block block, int meta) {
		if(block instanceof BlockMicroblockBase)
			((BlockMicroblockBase)block).renderInvBlock(render, meta);
		else
			BlockMicroblockBase.renderInvBlockStatic(render, block, meta);
	}
	
	/**
	 * If there is a microblock container block at the specified coordinates, this function will save the parts in that block,
	 * place a new block, restore the parts, and return true.
	 * If there is not a microblock container block at the specified coordinates, or placing the block fails, it will
	 * return false.
	 * The new block must have a tile entity which implements IMicroblockSupporterTile and has a non-null IMicroblockCoverSystem.
	 */
	public static boolean mergeIntoMicroblockContainer(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int l, int newBlockID, int newMetadata)
	{
		Block microblockContainerBlock = MicroblockSystem.getMicroblockContainerBlock();
		if(microblockContainerBlock == null || world.getBlockId(x, y, z) != microblockContainerBlock.blockID)
			return false;

		IMicroblockSupporterTile tm = (IMicroblockSupporterTile)world.getBlockTileEntity(x, y, z);
		MicroblockCoverSystem oldCI = tm.getCoverSystem();
		if(!world.setBlock(x, y, z, newBlockID, newMetadata, 2))
			return false;
		
		IMicroblockSupporterTile tcb = (IMicroblockSupporterTile)world.getBlockTileEntity(x, y, z);
		MicroblockCoverSystem newCI = tcb.getCoverSystem();
		
		for(Part p : oldCI.getAllParts())
			newCI.addPart(p);
		Block b = Block.blocksList[newBlockID];
		
        b.onBlockPlacedBy(world, x, y, z, entityplayer, itemstack);
		return true;
	}
	
}
