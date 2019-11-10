package bluidings;

import bluidings.Interfaces.Floor;
import bluidings.Interfaces.Building;
import bluidings.Interfaces.Space;

public class PlacementExchanger {

    /*
    Метод проверки возможности обмена помещениями. 
    Передаются две ссылки на объекты типа Space. Метод возвращает true, если 
    общая площадь и количество комнат в помещениях равны, 
    и false в других случаях.
     */
    public static boolean isExchangePossible(Space first, Space second) {
        return first.getArea() == second.getArea() && first.getRoomCount() == second.getRoomCount();
    }

    /*
    Метод проверки возможности обмена этажами. Методу передаются две ссылки на 
    объекты типа Floor. Метод возвращает true, если общая площадь этажей и 
    количество помещений равны, и false в других случаях.
     */
    public static boolean isExchangePossible(Floor first, Floor second) {
        return first.getSpaceCount() == second.getSpaceCount() && first.getSumArea() == second.getSumArea();
    }

    /*
    Метод должен проверять возможность обмена помещениями и допустимость 
    номеров помещений, выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws IndexChangeableSpacesException {
        if (!isExchangePossible(floor1.getSpace(index1), floor2.getSpace(index2))) {
            throw new IndexChangeableSpacesException();
        }
        Space temp = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, temp);
    }

    /*
    Методу передаются две ссылки типа Building и номера соответствующих этажей. 
    Метод должен проверять возможность обмена этажами и допустимость номеров этажей, 
    выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws IndexChangeableSpacesException {
        if (!isExchangePossible(building1.getFloor(index1), building2.getFloor(index2))) {
            throw new IndexChangeableSpacesException();
        }
        Floor temp = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, temp);
    }
}
