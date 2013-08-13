package teamcerberus.cerberuscore.multiblock;

public class MBBox {
	private float x, y, z, width, height, depth;
	private String textureName;
	
	public MBBox(float x, float y, float z, float width, float height, float depth, boolean thisForm, String textureName){
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = thisForm ? width : width - x;
		this.height = thisForm ? height : height - y;
		this.depth = thisForm ? depth : depth - z;
		this.textureName = textureName;
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
	
	public String getTextureName(){
		return textureName;
	}
}
