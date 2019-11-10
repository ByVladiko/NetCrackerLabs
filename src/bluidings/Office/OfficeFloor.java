package bluidings.Office;

import bluidings.DwellingBuilding.Flat;
import bluidings.Floor;
import bluidings.Space;
import bluidings.SpaceIndexOutOfBoundsException;
import utils.OneWayList.OneWayList;

public class OfficeFloor implements Floor {
    private OneWayList<Space> offices;

    
    public OfficeFloor(OneWayList<Space> offices) {  // Конструктор может принимать массив офисов этажа
        this.offices = offices;
    }
    
    public void removeAt(int index) {
        if(index < 0 || index >= offices.getCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        offices.removeAt(index);
    }

    public OfficeFloor(int countOffices) {  // Конструктор может принимать количество офисов на этаже
        if(countOffices <= 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        offices = new OneWayList<>();
        for (int i = 0; i < countOffices; i++) {
            offices.add(new Office(0, 0));
        }
    } 
    
    public Space[] getArrSpaces() {
        Space[] arr = new Space[offices.getCount()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = offices.getNode(i).getValue();
        }
        return arr;
    }

    // Геттеры и сеттеры++
    public OneWayList<Space> getSpaces() {
        return offices;
    } 

    public void setFloor(OneWayList<Space> offices) {
        this.offices = offices;
    }
    //Геттеры и сеттеры--
    
    public Space getSpace(int numOffice) { // Метод получения офиса по его номеру на этаже
           return getSpaces().getNode(numOffice).getValue();
    }
    
    public double getSumArea() {  // Метод получения общей площади помещений этажа
        double SumArea = 0;
        for (int i = 0; i < offices.getCount(); i++) {
            SumArea = SumArea + getSpace(i).getArea();
        }
        return SumArea;
    }
    
    public int getSumRoomCount() { // Метод получения общего числа комнат этажа
        int SumRoomCount = 0;
        for (int i = 0; i < offices.getCount(); i++) {
            SumRoomCount = SumRoomCount + getSpace(i).getRoomCount();
        }
        return SumRoomCount;
    }
    
    public int getSpaceCount() {
        return offices.getCount();
    }
    
    public void setSpace(int numOffice, Space newOffice) { // Метод изменения офиса по его номеру на этаже и ссылке на обновленный офис
        if(numOffice < 0 || numOffice >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        getSpace(numOffice).setArea(newOffice.getArea());
        getSpace(numOffice).setRoomCount(newOffice.getRoomCount());
    }
    
    public Space getBestSpace() { // Метод получения самого большого по площади офиса этажа
        Space BestAreaOffice = new Office(0);
        for (int i = 0; i < offices.getCount(); i++) {
            if(getSpace(i).getArea() > BestAreaOffice.getArea()) {
                BestAreaOffice = getSpace(i); 
            }
        }
        return BestAreaOffice;
    }

    @Override
    public void setArrSpaces(Space[] newFloor) {
        OneWayList<Space> floor = new OneWayList<>();
        for (int i = 0; i < newFloor.length; i++) {
            floor.add(newFloor[i]);
        }
        setFloor(floor);
    }
}
