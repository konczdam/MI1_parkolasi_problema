import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Level {

    private int length, width;
    private int[][] map;

    public Level(int length, int width) {
        this.length = length;
        this.width = width;
        map = new int[length][width];
    }

    public void printResult() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                res.append(map[i][j]);

                if (j == width - 1)
                    res.append('\n');
                else
                    res.append('\t');
            }
        }
        return res.toString();

    }

    public void placeVehicle(Vehicle v) {
        List<Position> position = getFittingPosition(v);
        placeVehicle(position.get(0), v);
    }

    public List<Position> getFittingPosition(Vehicle v) {
        Position basic, rotated;
        List<Position> res = new ArrayList<Position>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                basic = checkForBasicPosition(i, j, v);
                rotated = checkForRotatedPosition(i, j, v);

                if (basic != null || rotated != null)
                    res.addAll(chooseEdgedPosition(basic, rotated).stream().filter((Position p) -> {
                        if (res.isEmpty())
                            return true;
                        Position last = res.get(res.size() - 1);
                        return p.getX() != last.getX() && p.getY() != last.getY();

                        /**
                         * Két egymás utáni positionnak el kell térnie mind x és y kordinátában. Ez full faszság,
                         * biztos össze lehet rakni olyan mapot amit így nem oldd meg a program.
                         */
                    }).collect(Collectors.toList()));
            }
        }

        return res;
    }


    // lényegében annyit csinál, hogy ha vmelyik Position-re lerakva a kocsi pont a pálya széléig érne,
// és a másikkal nem, akkor azt rakja előrébb, amelyikkel a pálya széléig érne.
    private List<Position> chooseEdgedPosition(Position basic, Position rotated) {
        List<Position> res = new ArrayList<>();

        if (basic != null)
            res.add(basic);
        if (rotated != null)
            res.add(rotated);

        if (res.size() == 2 && !basic.isEdged() && rotated.isEdged()) {
            res.remove(rotated);
            res.add(0, rotated);
        }

        return res;

    }

    private Position checkForBasicPosition(int y, int x, Vehicle v) {
        boolean edged = false;
        int numOfBookedPlaces = 0;

        for (int i = y; i < Math.min(y + v.length, length); i++) {
            for (int j = x; j < Math.min(x + v.width, width); j++) {
                numOfBookedPlaces++;
                if (map[i][j] != 0)
                    return null;
                if (i == length - 1 || j == width - 1)
                    edged = true;

            }
        }
        return numOfBookedPlaces == v.getArea() ? new Position(false, y, x, edged) : null;
    }

    private Position checkForRotatedPosition(int i, int j, Vehicle v) {
        Position res = checkForBasicPosition(i, j, new Vehicle(v.number, v.width, v.length));
        if (res != null)
            res.setRotated(true);
        return res;
    }

    public void placeVehicle(Position position, Vehicle v) {
        if (position == null)
            return;
        Vehicle vehicleToPlace = position.isRotated() ? new Vehicle(v.number, v.width, v.length) : v;

        for (int i = position.getY(); i < position.getY() + vehicleToPlace.length; i++) {
            for (int j = position.getX(); j < position.getX() + vehicleToPlace.width; j++) {
                if (map[i][j] != 0)
                    throw new RuntimeException("problem");
                map[i][j] = v.number;
            }
        }
	}

    public int getLength() {
        return length;
    }


    public int getWidth() {
        return width;
    }


    public void removeVehicleFromMap(Vehicle vehicle, Position p) {
        int y = p.getY();
        int x = p.getX();
        int length = p.isRotated() ? vehicle.width : vehicle.length;
		int width = p.isRotated() ? vehicle.length : vehicle.width;
        for (int i = y; i < y + length; i++)
            for (int j = x; j < x + width; j++)
                map[i][j] = 0;
    }

}
