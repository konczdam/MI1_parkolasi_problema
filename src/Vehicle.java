
public class Vehicle implements Comparable<Vehicle> {
	int number, length, width,area;

	public Vehicle(int number, int length, int width){
		this.number = number;
		this.length = length;
		this.width = width;
		area = width*length;
	}

	public Vehicle(Vehicle other) {
		this.number = other.number;
		this.length = other.length;
		this.width = other.width;
		area = width*length;
	}
	
	public int getNumber() {
		return number;
	}

	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}
	public int getArea() {
		return area;
	}


	@Override
	public int compareTo(Vehicle o) {
		return new Integer(o.getArea()).compareTo(new Integer(getArea()));
	}
	
	@Override
	public String toString() {
		return length + " " + width;
		
	}

	public void rotate() {
		int temp = width;
		width = length;
		length = temp;
		
	}
	
	
	
	
}
