
public class Vehicle implements Comparable<Vehicle> {
    int number, length, width, area;

    public Vehicle(int number, int length, int width) {
        this.number = number;
        this.length = length;
        this.width = width;
        area = width * length;
    }

    public int getArea() {
        return area;
    }

    @Override
    public int compareTo(Vehicle o) {
        int i = this.getArea(), value = o.getArea();
        if (value == i)
            return 0;
        return value > i ? 1 : -1;
    }

    @Override
    public String toString() {
        return length + " " + width;

    }

}
