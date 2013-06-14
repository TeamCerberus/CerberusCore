package teamcerberus.cerberuscore.multiblock;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileMultiblock extends TileEntity{
	private ArrayList<MBBox> boxes = new ArrayList<MBBox>();
	
	public TileMultiblock(){
		boxes.add(new MBBox(0, 0, 0, 0.5f, 0.5f, 0.1f));
	}
	
	public boolean isSolid(ForgeDirection side) {
		return false;
	}

	public ArrayList<MBBox> getBoxes() {
		return boxes;
	}

}
