package Factory;

import buildings.DwellingBuilding.Flat;
import buildings.DwellingBuilding.Hotel.Hotel;
import buildings.DwellingBuilding.Hotel.HotelFloor;
import buildings.Interfaces.Building;
import buildings.Interfaces.BuildingFactory;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;

public class HotelFactory implements BuildingFactory {

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
        return new HotelFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Hotel(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }
    
}
