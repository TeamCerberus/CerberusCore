package teamcerberus.cerberuscore.override;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class IDResolver {
	public static int getFreeBlockID() {
		for (int i = 1; i < Block.blocksList.length; i++) {
			if (Block.blocksList[i] != null) {
				continue;
			}
			if (Item.itemsList[i] != null) {
				continue;
			}
			return i;
		}
		return -1;
	}

	public static int getFreeItemID() {
		for (int i = Item.shovelIron.itemID; i < Item.itemsList.length; i++) {
			if (i < Block.blocksList.length && Block.blocksList[i] != null) {
				continue;
			}
			if (Item.itemsList[i] == null) {
				continue;
			}
			return i;
		}
		return -1;
	}

}
