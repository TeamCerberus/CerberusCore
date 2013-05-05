package teamcerberus.cerberuscore.override;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.ItemData;

public class IDReplacer {
	public static void replaceBlock(int blockId, Block block) {
		clearBlock(block.blockID);

		Block.blocksList[blockId] = block;
		block.blockID = blockId;
		Block.opaqueCubeLookup[blockId] = block.isOpaqueCube();
		Block.lightOpacity[blockId] = block.isOpaqueCube() ? 255 : 0;
		Block.canBlockGrass[blockId] = !block.blockMaterial.getCanBlockGrass();
	}

	public static void replaceItem(int itemId, Item item) {
		clearItem(item.itemID);

		Item.itemsList[itemId] = item;
		GameData.newItemAdded(item);
	}

	public static void clearBlock(int blockId) {
		Block.blocksList[blockId] = null;
		Block.opaqueCubeLookup[blockId] = false;
		Block.lightOpacity[blockId] = 0;
		Block.canBlockGrass[blockId] = false;
	}

	public static void clearItem(int itemId) {
		Item.itemsList[itemId] = null;
		try {
			Map<Integer, ItemData> idMap = ObfuscationReflectionHelper
					.getPrivateValue(GameData.class, null, "idMap");
			idMap.remove(itemId);
		} catch (Exception e) {}
	}
}
