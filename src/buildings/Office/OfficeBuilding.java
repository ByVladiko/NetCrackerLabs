package buildings.Office;

import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.FloorIndexOutOfBoundsException;
import buildings.Interfaces.Space;
import buildings.SpaceIndexOutOfBoundsException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.TwoWayList.TwoWayList;

public class OfficeBuilding implements Building, Serializable {

    TwoWayList<Floor> officeBuilding;

    public OfficeBuilding(int countFloor, int[] countOffices) {  // Конструктор может принимать количество этажей и массив количества офисов по этажам
        if (countFloor <= 0 || countFloor != countOffices.length) {
            throw new FloorIndexOutOfBoundsException();
        }
        TwoWayList<Floor> ListFloors = new TwoWayList<>();
        for (int i = 0; i < countFloor; i++) {
            ListFloors.add(new OfficeFloor(countOffices[i]));
        }
    }

    public OfficeBuilding(Floor[] officeFloor) { // Конструктор может принимать массив этажей офисного здания
        if (officeFloor.length == 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        this.officeBuilding = new TwoWayList<>();
        for (int i = 0; i < officeFloor.length; i++) {
            officeBuilding.add(officeFloor[i]);
        }
    }

    public Floor[] getFloors() { // Метод получения массива этажей офисного здания
        Floor[] floor = new Floor[officeBuilding.getCount()];
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            floor[i] = officeBuilding.getNode(i).getValue();
        }
        return floor;
    }

    public Floor getFloor(int index) { // Метод получения этажа офисного здания по номеру
        if (index < 0 || index >= officeBuilding.getCount()) {
            throw new FloorIndexOutOfBoundsException();
        }
        return officeBuilding.getNode(index).getValue();
    }

    public int getSumSpaces() { // Метод получения общего количества офисов здания
        int SumOffices = 0;
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            SumOffices = SumOffices + getFloor(i).getSpaceCount();
        }
        return SumOffices;
    }

    public double getSumArea() { // Метод получения общей площади помещений здания
        double SumArea = 0;
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            SumArea = SumArea + getFloor(i).getSumArea();
        }
        return SumArea;
    }

    public int getSumRoomCount() { // Метод получения общего количества комнат здания
        int SumRoomCount = 0;
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            SumRoomCount = SumRoomCount + getFloor(i).getSumRoomCount();
        }
        return SumRoomCount;
    }

    public void setFloor(int index, Floor newOfficeFloor) { // Метод изменения этажа по его номеру в здании и ссылке на объект нового этаж
        if (index < 0 || index >= officeBuilding.getCount()) {
            throw new FloorIndexOutOfBoundsException();
        }
        officeBuilding.getNode(index).setValue(newOfficeFloor);
    }

    public Space getSpace(int index) { // Метод получения объекта офиса по его номеру в офисном здании
        int count = 0;
        for (int i = 0; i < this.getFloors().length; i++) {
            Floor temp = this.getFloor(index);
            for (int j = 0; j < temp.getArrSpaces().length; j++) {
                if (index == count) {
                    return temp.getSpace(j);
                }
                count++;
            }
        }
        return null;
    }

    public void setSpace(int index, Space newOffice) { // Метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса
        if (index < 0 || index >= getSumSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        getSpace(index).setArea(newOffice.getArea());
        getSpace(index).setRoomCount(newOffice.getRoomCount());
    }

    public void insertAt(int index, Space newOffice) { // Метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса
        if (index < 0 || index >= getSumSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int count = 0;
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            Floor temp = officeBuilding.getNode(i).getValue();
            for (int j = 0; j < temp.getSpaceCount(); j++) {
                if (index == count) {
                    temp.insertAt(j, newOffice);
                }
                count++;
            }
        }
    }

    public void removeAt(int index) { // Метод удаления офиса по его номеру в здании
        if (index < 0 || index >= getSumSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int count = 0;
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            Floor temp = officeBuilding.getNode(i).getValue();
            for (int j = 0; j < temp.getSpaceCount(); j++) {
                if (index == count) {
                    temp.removeAt(index);
                }
                count++;
            }
        }
    }

    public Space getBestSpace() { // Метод получения самого большого по площади офиса здания
        Space BestAreaOffice = new Office(0);
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            if (officeBuilding.getNode(i).getValue().getBestSpace().getArea() > BestAreaOffice.getArea()) {
                BestAreaOffice = officeBuilding.getNode(i).getValue().getBestSpace();
            }
        }
        return BestAreaOffice;
    } //check

    public int getSumFloorCount() {
        return officeBuilding.getCount();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("OfficeBuilding (" + officeBuilding.getCount() + ", ");
        for (int i = 0; i < officeBuilding.getCount(); i++) {
            sb.append(getFloor(i).toString());
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof OfficeBuilding)) {
            return false;
        }
        OfficeBuilding building = (OfficeBuilding) object;
        for (int i = 0; i < getSumFloorCount(); i++) {
            if (getFloor(i).equals(building.getFloor(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = getSumFloorCount();
        for (int i = 0; i < getSumFloorCount(); i++) {
            result = result ^ getFloor(i).hashCode();
        }
        return result;
    }

    public Object clone() {
        Floor[] floors = new Floor[getSumFloorCount()];
        for (int i = 0; i < getSumFloorCount(); i++) {
            floors[i] = (Floor) getFloor(i).clone();
        }
        OfficeBuilding result = new OfficeBuilding(floors);
        return result;
    }
}
