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
	Predicate<Vehicle> bigVehiclePredicate;

	public Game(InputStream inputStrem) {
		Scanner in = new Scanner(inputStrem);
		int length = in.nextInt();
		int width = in.nextInt();

		level = new Level(length, width);

		initvehicles(in);
		initPredicate();
		vehiclesToPlace = vehiclesToPlace.stream().sorted().collect( Collectors.toList());

	}
	
	public Game(Game other) {
		this.level = new Level(other.level);
		this.numberOfVehicles = other.numberOfVehicles;
		this.vehiclesToPlace = new ArrayList<>(other.vehiclesToPlace);
		this.bigVehiclePredicate = other.bigVehiclePredicate;
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
				return t.length == level.getLength() || t.length == level.getWidth() || t.width == level.getWidth()
						|| t.width == level.getLength();
			}
		};
	}

	public void printresults() {
		level.printResult();
	}

	public boolean solve(boolean asd) {
		placeBiggestVehicles();
		return solve();
	}
	
	public boolean solve() {
//		if(vehiclesToPlace.size() == 1) {
//			System.out.println("mindj kész");
//		}
		
//		Collections.sort(vehiclesToPlace);
		if(vehiclesToPlace.size() == 0) {
			this.printresults();
			System.exit(0);
		}
		
		
		
		
		for(int i = 0; i < vehiclesToPlace.size(); i++) {
			Vehicle nextVehicle = vehiclesToPlace.get(i);
			boolean asd = level.placeVehicle(nextVehicle);
			if(!asd)
				return false;
			vehiclesToPlace.remove(nextVehicle);
			
			Game newGame = new Game(this);
			if(newGame.solve())
				return true;
			
			else {
				level.removeVehicleFromMap(nextVehicle.number);
				vehiclesToPlace.add(0, nextVehicle);
				if(nextVehicle.getArea() == 1)
					return false;
			}
				
			
		}
		if(vehiclesToPlace.size() != 0)
			return false;
		
		printresults();
		return true;
	}

	

	private void placeBiggestVehicles() {
		List<Vehicle> bigVehicles = getVehiclesToPlace().stream().filter(bigVehiclePredicate)
				.collect(Collectors.toList());
		for (Vehicle v : bigVehicles) {
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
	
	@Override
	public String toString() {
		return level.toString();
	}

}
