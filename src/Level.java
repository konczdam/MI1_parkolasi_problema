import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {
	
	private int length, width;
	private int[][] map;
	
	public Level(int length, int width) {
		this.length = length;
		this.width = width;
		map = new int[length][width];
	
	}
	
	public Level(Level other) {
		this.length = other.length;
		this.width = other.width;
		map = new int[length][width];
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				this.map[i][j] = other.getMap()[i][j];
			}
		}
	}
	
	
	public void printResult() {
		for(int i = 0; i < length; i++) {
			StringBuilder res = new StringBuilder();
			for(int j = 0; j < width; j++) {
				res.append(map[i][j]);
				
				if(j == width - 1)
					res.append('\n');
				else
					res.append('\t');
			}
			System.out.println(res.toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				res.append(map[i][j]);
				
				if(j == width - 1)
					res.append('\n');
				else
					res.append('\t');
			}
		}
		return res.toString();
		
	}



	public boolean placeVehicle(Vehicle v) {
		List<Position> position = getFittingPositin(v);
		return placeVehicle(position.get(0), v);
	}
	
	


	public List<Position> getFittingPositin(Vehicle v) {
		Position basic, rotated;
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				basic = checkforBasicPosition(i,j, v);
				rotated = checkforRotatedPosition(i,j,v);
				
				if(basic != null || rotated != null)
					return chooseEdgedPosition( basic, rotated );
			}
		}

		
		return null;
	}



	private List<Position> chooseEdgedPosition(Position basic, Position rotated) {
		List<Position> res = new ArrayList<>();
		
		if(basic != null)
			res.add(basic);
		if(rotated != null)
			res.add(rotated);
		
		if(res.size() == 2 && !basic.isEdged() && rotated.isEdged()) {
			res.remove(rotated);
			res.add(0,rotated);
		}
		
		return res;
		
	}


	private Position checkforBasicPosition(int y, int x, Vehicle v) {
		boolean edged = false;
		int numOfBookedPlaces = 0;
		for(int i = y; i < Math.min(y + v.length, length); i++) {
			for(int j = x; j < Math.min(x + v.width, width) ; j++) {
				numOfBookedPlaces++;
				if(map[i][j] != 0)
					return null;
				if(i == length - 1 || j == width - 1)
					edged = true;
					
			}
		}
		return numOfBookedPlaces == v.getArea()? new Position(false, y,x, edged) : null;
	}

	private Position checkforRotatedPosition(int i, int j, Vehicle v) {
		Position res = checkforBasicPosition(i, j, new Vehicle(v.number, v.width, v.length));
		if(res!= null)
			res.setRotated(true);
		return res;
	}

	public boolean placeVehicle(Position position, Vehicle v) {
		if(position == null)
			return false;
		Vehicle VehicletoPlace = position.isRotated() ? new Vehicle(v.number, v.width, v.length) : v;
		
		for(int i = position.getY(); i < position.getY() + VehicletoPlace.length; i++) {
			for(int j = position.getX(); j < position.getX() + VehicletoPlace.width ; j++) {
				if(map[i][j] != 0)
					throw new RuntimeException("problem");
				map[i][j] = v.number;
			}
		}
		
		return true;
		
		
	}

	public int getLength() {
		return length;
	}
	
	
	public int getWidth() {
		return width;
	}
	
	
	public int[][] getMap() {
		return map;
	}

	public void removeVehicleFromMap(int number) {
		for(int i = 0; i < length; i++)
			for(int j = 0; j < width; j++)
				if(map[i][j] == number)
					map[i][j] = 0;
		
	}
	
}
