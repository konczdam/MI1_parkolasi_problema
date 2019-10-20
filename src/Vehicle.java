
public class Vehicle implements Comparable<Vehicle> {
	int number, length, width,area;
	Position position;




	public Vehicle(int number, int length, int width){
		this.number = number;
		this.length = length;
		this.width = width;
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

	public Position getPosition() {
		return position;
	}
	
	
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int compareTo(Vehicle o) {
		int i = this.getArea(), value = o.getArea();
		
		
		if(value == i)
			return 0;
		
		return value > i ? 1 : -1;
		
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
