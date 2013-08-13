package teamcerberus.cerberuscore.multiblock;

import net.minecraft.util.MovingObjectPosition;

public class MBRaytraceResult {
	public MBBox hitPart;
	public MovingObjectPosition movingObjectPosition;
	
	public MBRaytraceResult(MBBox hitPart, MovingObjectPosition movingObjectPosition) {
		this.hitPart = hitPart;
		this.movingObjectPosition = movingObjectPosition;
	}
}
