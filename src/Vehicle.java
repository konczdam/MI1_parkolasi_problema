
public class Vehicle implements Comparable<Vehicle> {
	int number, length, width;

	public Vehicle(int number, int length, int width){
		this.number = number;
		this.length = length;
		this.width = width;
	}

	public Vehicle(Vehicle other) {
		this.number = other.number;
		this.length = other.length;
		this.width = other.width;
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
		return width*length;
	}


	@Override
	public int compareTo(Vehicle o) {
		return new Integer(o.getArea()).compareTo(new Integer(getArea()));
	}
	
	
	
	
}
