package Factory;

import buildings.DwellingBuilding.Dwelling;
import buildings.DwellingBuilding.DwellingFloor;
import buildings.DwellingBuilding.Flat;
import buildings.Interfaces.Building;
import buildings.Interfaces.BuildingFactory;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;

public class DwellingFactory implements BuildingFactory {

    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        return new Flat(roomsCount, area);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new DwellingFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Dwelling(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling(floors);
    }
    
}
