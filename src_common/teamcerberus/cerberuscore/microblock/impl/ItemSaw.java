package teamcerberus.cerberuscore.microblock.impl;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSaw extends Item {

	public ItemSaw(int i) {
		super(i);
		maxStackSize = 1;
	}
	
	@Override
	public boolean hasContainerItem() {
		return true;
	}
	
	@Override
	public Item getContainerItem() {
		return this;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack is) {
		return false;
	}
}
