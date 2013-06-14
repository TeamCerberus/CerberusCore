package teamcerberus.cerberuscore.multiblock;

public class MBBox {
	private float x, y, z, width, height, depth;
	
	public MBBox(float x, float y, float z, float width, float height, float depth){
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getZ(){
		return z;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getDepth(){
		return depth;
	}
}
