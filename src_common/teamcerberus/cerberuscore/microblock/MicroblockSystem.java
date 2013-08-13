package teamcerberus.cerberuscore.microblock;


import java.util.ArrayList;
import java.util.HashMap;

import teamcerberus.cerberuscore.microblock.impl.IMicroblockSupporterTile;
import teamcerberus.cerberuscore.microblock.impl.ItemMicroblock;
import teamcerberus.cerberuscore.microblock.part.DefaultPartType;
import teamcerberus.cerberuscore.microblock.part.EnumPartClass;
import teamcerberus.cerberuscore.microblock.part.Part;
import teamcerberus.cerberuscore.microblock.part.PartIDInUseException;
import teamcerberus.cerberuscore.microblock.part.PartType;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class MicroblockSystem{
	
	/*
	 * Part IDs are bitfields.
	 * 
	 * Bits 20-31 are block ID. (12 bits)
	 * If block ID is nonzero, part is an auto-detected or third-party one.
	 * 		10-19 are ID-specific. (10 bits)
	 * 		bit 9 is 1 for parts added by the system, 0 for other parts.
	 * 		bits 5-8 are reserved.
	 * 		bits 3-4 are part type.
	 * 		bits 0-2 are part size. 
	 * 
	 * If block ID is zero, part is one that was manually added in postinit.
	 * 		bits 5-19 are manually chosen ID.
	 * 		bits 3-4 are part type.
	 * 		bits 0-2 are part size.
	 * 
	 * All vanilla-based parts are manually added in postinit.
	 */
	public final static HashMap<Integer, PartType<?>> parts = new HashMap<Integer, PartType<?>>();
	
	public static ArrayList<Integer> neiPartIDs = new ArrayList<Integer>();
	
	
	public static void registerManualParts(int n, Block block, int blockMeta) {
		registerManualParts(n, block, blockMeta, block, blockMeta);
	}
	
	public static void registerManualParts(int n, Block block) {
		registerManualParts(n, block, 0);
	}
	
	private static class PartRegistrationType {
		public EnumPartClass clazz;
		public double size;
		public PartRegistrationType(EnumPartClass c, double s)
		{
			clazz = c;
			size = s;
		}
	}
	
	private static PartRegistrationType blockparts[] = new PartRegistrationType[] {
		new PartRegistrationType(EnumPartClass.Panel, 1.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 2.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 3.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 4.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 5.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 6.0/8.0),
		new PartRegistrationType(EnumPartClass.Panel, 7.0/8.0),
		null,
		new PartRegistrationType(EnumPartClass.Strip, 1.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 2.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 3.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 4.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 5.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 6.0/8.0),
		new PartRegistrationType(EnumPartClass.Strip, 7.0/8.0),
		null,
		new PartRegistrationType(EnumPartClass.Corner, 1.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 2.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 3.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 4.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 5.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 6.0/8.0),
		new PartRegistrationType(EnumPartClass.Corner, 7.0/8.0),
		null,
		new PartRegistrationType(EnumPartClass.HollowPanel, 1.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 2.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 3.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 4.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 5.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 6.0/8.0),
		new PartRegistrationType(EnumPartClass.HollowPanel, 7.0/8.0),
		null,
	};
	
	private static void registerManualParts(int n, Block block, int meta, Block craftingBlock, int craftingMeta) {
		registerParts(n*64, block, meta, craftingBlock, craftingMeta, false);
	}
	
	private static void registerParts(int partIDBase, Block block, int meta, Block craftingBlock, int craftingMeta, boolean ignoreNameCheck) {
//		assert(blockparts.length == 32);
		
		String unlocalizedBlockName = new ItemStack(block, 1, meta).getItemName() + ".name";
		
//		for(int k = 0; k < 7; k++)
//		{
//			// making hollow covers
//			RecipeHollowCover.addMap(partIDBase + k, partIDBase + k + 24);
//			// reverting hollow covers
//			RecipeUnHollowCover.addMap(partIDBase + k + 24, partIDBase + k);
//			
//			// cutting panels into strips
//			RecipeHorizontalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase + k), ItemMicroblock.getStackWithPartID(partIDBase + k + 8, 2));
//			
//			// cutting strips into corners
//			RecipeHorizontalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase + k + 8), ItemMicroblock.getStackWithPartID(partIDBase + k + 16, 2));
//			
//			// combining corners into strips
//			RecipeCombineTwo.addMap(partIDBase + k + 16, partIDBase + k + 8);
//			
//			// combining strips into panels
//			RecipeCombineTwo.addMap(partIDBase + k + 8, partIDBase + k);
//		}
//		
//		// combining multiple panels
//		RecipeCombineSeveral.addMap(partIDBase, new ItemStack(craftingBlock, 1, craftingMeta));
//		
//		// combining multiple hollow panels
//		RecipeCombineSeveral.addMap(partIDBase + 24, new ItemStack(craftingBlock, 1, craftingMeta));
//		
//		// cutting full blocks/slabs/panels
//		RecipeVerticalCut.addMap(new BlockMetaPair(craftingBlock.blockID, craftingMeta), ItemMicroblock.getStackWithPartID(partIDBase+3, 2));
//		RecipeVerticalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase+3), ItemMicroblock.getStackWithPartID(partIDBase+1, 2));
//		RecipeVerticalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase+1), ItemMicroblock.getStackWithPartID(partIDBase+0, 2));
//		
//		// cutting hollow slabs/panels
//		RecipeVerticalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase+27), ItemMicroblock.getStackWithPartID(partIDBase+25, 2));
//		RecipeVerticalCut.addMap(new BlockMetaPair(microblockContainerBlock.blockID, partIDBase+25), ItemMicroblock.getStackWithPartID(partIDBase+24, 2));
		
		for(int k = 0; k < blockparts.length; k++)
			if(blockparts[k] != null)
			{
				
				PartType<Part> type = new DefaultPartType(partIDBase+k,
					blockparts[k].clazz,
					blockparts[k].size, unlocalizedBlockName,
					block,
					meta
				);
				registerPartType(type);
			}
	}
	
	public static void registerPartType(PartType<?> type) {
		int id = type.getID();
		if(parts.containsKey(id))
			throw new PartIDInUseException(id, parts.get(id), type);
		parts.put(id, type);
		neiPartIDs.add(id);
	}
	
	public static MicroblockCoverSystem createMicroblockCoverSystem(IMicroblockSupporterTile tile) {
		return new MicroblockCoverSystem(tile);
	}
	
	public static void addCuttableBlock(Block block, int meta) {
		if(block.blockID < 1 || block.blockID > 4095)
			throw new IllegalArgumentException("BlockID must be between 1 and 4095 inclusive");
		if(meta < 0 || meta > 1023)
			throw new IllegalArgumentException("meta must be between 0 and 1023 inclusive");
		registerParts(((block.blockID & 4095) << 20) | ((meta & 1023) << 10), block, meta, block, meta, true);
	}
	
	public static PartType<?> getPartTypeByID(int id) {
		return parts.get(id);
	}
	
	public static Block getMicroblockContainerBlock() {
		return MultiblockManager.microblockContainerBlock;
	}
	
	public static ItemStack partTypeIDToItemStack(int id, int stackSize) throws IllegalArgumentException {
		if(!parts.containsKey(id))
			throw new IllegalArgumentException("No part with ID "+id+" (hex: "+Integer.toHexString(id)+")");
		return ItemMicroblock.getStackWithPartID(id, stackSize);
	}
	
		
	public int itemStackToPartID(ItemStack stack) throws NullPointerException, IllegalArgumentException {
		if(stack.itemID != MultiblockManager.microblockContainerBlock.blockID)
			throw new IllegalArgumentException("Not a stack of microblocks");
		return ItemMicroblock.getPartTypeID(stack);
	}
}
