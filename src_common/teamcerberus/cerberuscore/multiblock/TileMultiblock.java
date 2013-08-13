package teamcerberus.cerberuscore.multiblock;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileMultiblock extends TileEntity{
	private ArrayList<MBBox> boxes = new ArrayList<MBBox>();
	private MBRaytraceResult selectedBox;
	
	public TileMultiblock(){
		boxes.add(new MBBox(0f, 0f, 0f, 1f, 1f, 0.1f, true, "example"));
		boxes.add(new MBBox(0f, 0f, 0.9f, 1f, 1f, 0.1f, true, "example"));
	}
	
	public boolean isSolid(ForgeDirection side) {
		return false;
	}

	public ArrayList<MBBox> getBoxes() {
		return boxes;
	}
	
	public MBRaytraceResult getSelectedBox(){
		return selectedBox;
	}
	
	public void setSelectedBox(MBRaytraceResult result){
		this.selectedBox = result;
	}

}
