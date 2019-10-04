import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	
	private Level level;
	private int numberOfVehicles;
	private List<Vehicle> vehiclesToPlace;
	Predicate<Vehicle> bigVehiclePredicate ;
	
	public Game(InputStream inputStrem) {
		Scanner in = new Scanner(inputStrem);
		int length = in.nextInt();
		int width = in.nextInt();
		
		level = new Level(length,width);
		
		initvehicles(in);
		initPredicate();
		
	}

	private void initvehicles(Scanner in) {
		numberOfVehicles = in.nextInt();
		vehiclesToPlace = new ArrayList<Vehicle>(numberOfVehicles);
		
		IntStream.range(1, numberOfVehicles + 1).forEach(i -> {
			vehiclesToPlace.add(new Vehicle(i, in.nextInt(), in.nextInt()));
		});
	}

	private void initPredicate() {
		bigVehiclePredicate = new Predicate<Vehicle>() {
			
			@Override
			public boolean test(Vehicle t) {
				return t.length == level.getLength() || t.length == level.getWidth() ||
					   t.width == level.getWidth() || t.width == level.getLength();	
			}
		};
	}

public void printresults() {
		level.printResult();
	}
	
	
	public void solve() {
		placeBiggestVehicles();
		for(Vehicle v: vehiclesToPlace)
			level.placeVehicle(v);
	}
	
	
	
	
	private void placeBiggestVehicles() {
		List<Vehicle> bigVehicles = getVehiclesToPlace().stream().filter(bigVehiclePredicate).collect(Collectors.toList());
		for(Vehicle v: bigVehicles) {
			level.placeVehicle(v);
			
		}
		vehiclesToPlace.removeAll(bigVehicles);
	}

	

	public Level getLevel() {
		return level;
	}

	public int getNumberOfVehicles() {
		return numberOfVehicles;
	}

	public List<Vehicle> getVehiclesToPlace() {
		return vehiclesToPlace;
	}
	
	
}
