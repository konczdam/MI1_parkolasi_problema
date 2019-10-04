
public class Position {
	private boolean rotated,edged;
	private int y, x;
	
	public Position(boolean rotated, int y, int x) {
		this.rotated = rotated;
		this.y = y;
		this.x = x;
	}
	
	public Position(boolean rotated, int y, int x, boolean edged) {
		this(rotated,y,x);
		this.edged = edged;
	}
	
	public boolean isRotated() {
		return rotated;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	public boolean isEdged() {
		return edged;
	}

	public void setRotated(boolean rotated) {
		this.rotated = rotated;
	}
}
