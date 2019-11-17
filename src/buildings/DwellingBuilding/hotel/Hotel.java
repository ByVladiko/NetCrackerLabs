package buildings.DwellingBuilding.Hotel;

import buildings.DwellingBuilding.Dwelling;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;

public class Hotel extends Dwelling {

    public Hotel(int floorsCount, int[] floors) {
        super(floorsCount, floors);
    }

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public int getStars() {
        int bestValue = 0;
        for (Floor floor : floors) {
            if (((HotelFloor) floor).getStars() < bestValue) {
                bestValue = ((HotelFloor) floor).getStars();
            }
        }
        return bestValue;
    }

    @Override
    public Space getBestSpace() {
        double[] coeffs = {0.25, 0.5, 1, 1.25, 1.5};
        Space bestSpace = null;
        double bestValue = 0;
        for (Floor floor : floors) {
            for (int j = 0; j < floor.getSpaceCount(); j++) {
                Floor tempFloor = floor;
                if (floor.getSpace(j).getArea() * coeffs[((HotelFloor) tempFloor).getStars() + 1] > bestValue) {
                    bestValue = floor.getSpace(j).getArea() * coeffs[((HotelFloor) tempFloor).getStars() + 1];
                    bestSpace = floor.getSpace(j);
                }
            }
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hotel (" + getStars() + ", " + floors.length);
        for (Floor floor : floors) {
            sb.append(", ");
            sb.append(floor.toString());
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        Hotel building = (Hotel) object;
        if (object == this) {
            return true;
        }
        if (!(object instanceof Hotel)) {
            return false;
        }
        if (getSumFloorCount() != building.getSumFloorCount()) {
            return false;
        }
        for (int i = 0; i < floors.length; i++) {
            if (floors[i].equals(building.getFloor(i)) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = floors.length;
        for (Floor floor : floors) {
            result = result ^ floor.hashCode();
        }
        return result;
    }
}
