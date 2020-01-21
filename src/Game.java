import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

	private Level level;
	private List<Vehicle> vehiclesToPlace;
	Predicate<Vehicle> bigVehiclePredicate;

	public Game(InputStream inputStream) {
		Scanner in = new Scanner(inputStream);
		int length = in.nextInt();
		int width = in.nextInt();
		level = new Level(length, width);
		initvehicles(in);
		vehiclesToPlace = vehiclesToPlace.stream().sorted().collect(Collectors.toList());
		initPredicate();
	}



	private void initvehicles(Scanner in) {
		int numberOfVehicles = in.nextInt();
		vehiclesToPlace = new ArrayList<>(numberOfVehicles);

		IntStream.range(1, numberOfVehicles + 1).forEach(i -> vehiclesToPlace.add(new Vehicle(i, in.nextInt(), in.nextInt())));
		
	}

	private void initPredicate() {
		bigVehiclePredicate = t -> t.length == level.getLength() || t.length == level.getWidth() || t.width == level.getWidth()
				|| t.width == level.getLength();
	}

	public void printResults() {
		level.printResult();
	}

	public boolean solveAndPrintResults() {

		if (vehiclesToPlace.size() == 0) {
			this.printResults();
			return true;
		}
		
			Vehicle nextVehicle = vehiclesToPlace.get(0);

			List<Position> fittingPositions = level.getFittingPosition(nextVehicle);
			if (fittingPositions != null && !fittingPositions.isEmpty()) {
				for (Position p : fittingPositions) {
					
					boolean vehiclePlacedSuccessfully = level.placeVehicle(p, nextVehicle);
					if (!vehiclePlacedSuccessfully)
						return false;
					
					vehiclesToPlace.remove(nextVehicle);
					
					if(solveAndPrintResults())
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
