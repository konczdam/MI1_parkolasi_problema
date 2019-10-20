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
		vehiclesToPlace = vehiclesToPlace.stream().sorted().collect(Collectors.toList());
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
				return t.length == level.getLength() || t.length == level.getWidth() || t.width == level.getWidth()
						|| t.width == level.getLength();
			}
		};
	}

	public void printresults() {
		level.printResult();
	}

	public boolean solve() {

		if (vehiclesToPlace.size() == 0) {
			this.printresults();
			return true;
		}
		
			Vehicle nextVehicle = vehiclesToPlace.get(0);

			List<Position> fittingPositions = level.getFittingPositin(nextVehicle);
			if (fittingPositions != null && !fittingPositions.isEmpty()) {
				for (Position p : fittingPositions) {
					
					boolean vehiclePlacedSuccesfully = level.placeVehicle(p, nextVehicle);
					if (!vehiclePlacedSuccesfully)
						return false;
					
					vehiclesToPlace.remove(nextVehicle);
					
					if(solve())
						return true;
					else 
						level.removeVehicleFromMap(nextVehicle, p);
					
				}
			}
			
			if(!vehiclesToPlace.contains(nextVehicle))
				vehiclesToPlace.add(0,nextVehicle);
			return false; 

	
	}

	public void placeBiggestVehicles() {
		List<Vehicle> bigVehicles = getVehiclesToPlace().stream().filter(bigVehiclePredicate)
				.collect(Collectors.toList());
		for (Vehicle v : bigVehicles) {
			level.placeVehicle(v);

		}
		vehiclesToPlace.removeAll(bigVehicles);
	}


	public List<Vehicle> getVehiclesToPlace() {
		return vehiclesToPlace;
	}

	@Override
	public String toString() {
		return level.toString();
	}

}
