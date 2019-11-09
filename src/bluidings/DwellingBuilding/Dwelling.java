package bluidings.DwellingBuilding;

import bluidings.Building;
import bluidings.Floor;
import bluidings.Space;

public class Dwelling implements Building {

    private int numFloor;
    private Floor[] floors;

    public Dwelling(int floorsAmount, int[] flatsAmount) {
        this.floors = new DwellingFloor[floorsAmount];
        for (int i = 0; i < floorsAmount; i++) {
            this.floors[i] = new DwellingFloor(flatsAmount[i]);
        }
    }

    public Dwelling(Floor[] floors) { // Конструктор может принимать массив этажей дома.
        this.floors = floors;
    }

    // Геттеры и сеттеры
    public int getNumFloor() {
        return numFloor;
    }

    public Floor[] getFloors() { // Метод получения массива этажей жилого дома
        return floors;
    }
    
    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public void setFloors(DwellingFloor[] floors) {
        this.floors = floors;
    }
    
    public int getSumFloorCount(){
        return floors.length;
    }

    public int getSumSpaces() { // Метод получения общего количества квартир дома
        int SumFlats = 0;
        for (Floor floor : floors) {
            SumFlats = SumFlats + floor.getSpaceCount();
        }
        return SumFlats;
    }

    public double getSumArea() { // Метод получения общей площади квартир всего дома
        double SumArea = 0;
        for (Floor floor : floors) {
            SumArea = SumArea + floor.getSumArea();
        }
        return SumArea;
    }

    public int getSumRoomCount() {  // Метод получения общего количества комнат дома
        int SumRoomCount = 0;
        for (Floor floor : floors) {
            SumRoomCount = SumRoomCount + floor.getSumRoomCount();
        }
        return SumRoomCount;
    }

    public Floor getFloor(int numFloor) { // Метод получения объекта этажа, по его номеру в доме
        return getFloors()[numFloor];
    }
    
    public int getFloorOnFlat(int numFlat) { // Метод получения индекса этажа по номеру квартиры в доме
        int count = 0;
        for (int i = 0; i < floors.length; i++) {
            Floor floor = floors[i];
            for (int j = 0; j < floors[i].getSpaceCount(); j++) {
                if(count == numFlat) {
                    return i;
                }       
            }
        }
        return 0;
    }

    public Space getSpace(int numFlat) { // Метод получения квартиры, по её номеру в доме
        int count = 0;
        for (Floor floor : floors) {
            Space[] floors = floor.getArrSpaces();
            for (Space flat : floors) {
                if (count == numFlat) {
                    return flat;
                }
                count++;
            }
        }
        return null;
    }

    public void setFloor(int numFloor, Floor newFloor) { // Метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
        getFloors()[numFloor] = newFloor;
    }

    public void setSpace(int flatNum, Space newFlat) { // Метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
        getSpace(flatNum).setArea(newFlat.getArea());
        getSpace(flatNum).setRoomCount(newFlat.getRoomCount());
    }
    
    public int getIndexFlatOnFloor (int numFlat) { // Метод получения индекса квартиры на этаже по номеру квартиры
        int count = 0;
        for (int i = 0; i < floors.length; i++) {
            for (int j = 0; j < floors[i].getSpaceCount(); j++) {
                if(count == numFlat){
                    return j;
                }       
            }
        }
        return 0;
    }
    
    public void addFlat(Flat newFlat) { // Метод добавления квартиры в дом на последний этаж
        Floor floor = floors[floors.length - 1];
        Floor newFloor = new DwellingFloor(floor.getSpaceCount() + 1);
        for (int i = 0; i < newFloor.getSpaceCount(); i++) {
            if (i == floor.getSpaceCount()) {
                newFloor.getArrSpaces()[i] = newFlat;
                break;
            }
            newFloor.getArrSpaces()[i] = floor.getArrSpaces()[i];
        }
        floors[floors.length - 1] = newFloor;
    }

    public void insertAt(int numFlat, Space newFlat) { // Метод добавления квартиры в дом по будущему номеру квартиры в доме 
    // (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры 
    // (количество этажей в доме при этом не увеличивается)
    Floor floor = floors[getFloorOnFlat(numFlat)];
    Floor newFloor = new DwellingFloor(floor.getSpaceCount() + 1);
        for (int i = 0, j = 0; i < newFloor.getSpaceCount(); i++) {
            if(i == getIndexFlatOnFloor(numFlat)){
                newFloor.getArrSpaces()[i] = newFlat;
                continue;
            }
            newFloor.getArrSpaces()[i] = floor.getArrSpaces()[j];
            j++;
        }
        floors[getFloorOnFlat(numFlat)] = newFloor;
    }

    public void removeAt(int numFlat) { //Метод удаления квартиры по ее номеру в доме
        int numFloor = 0; //Номер этажа
        int temp = 0;
        for (int i = 0; i < getFloors().length; i++) {
            Floor floor = getFloors()[i];
            for (int j = 0; j < floor.getArrSpaces().length; j++) {
                Space flats = floor.getSpace(j);
                if (temp == numFlat) {
                    numFloor = i;
                    break;
                }
                temp++;
            }
        }
        Flat[] newFloor = new Flat[getFloor(numFloor).getArrSpaces().length - 1];
        int count = 0; // Счетчик комнат для нового этажа
        for (int i = 0; i < getFloor(numFloor).getArrSpaces().length; i++) {
            if (numFlat == i) {
                continue;
            }
            newFloor[count] = (Flat) getFloor(numFloor).getArrSpaces()[i];
            count++;
        }
        floors[numFloor].setFlats(newFloor);
    }

    public Space getBestSpace() { // Метод получения самой большой по площади квартиры дома
        Space bestFlatSpace = null;
        for (Floor floor : floors) {
            Space[] flats = floor.getArrSpaces();
            for (Space flat : flats) {
                if (flat.getArea() > bestFlatSpace.getArea()) {
                    bestFlatSpace = flat;
                }
            }
        }
        return bestFlatSpace;
    }
    
    public static void quickSort(Space[] array, int low, int high) { // Быстрая сортировка
        if (array.length == 0) {
            return;//завершить выполнение если длина массива равна 0
        }
        if (low >= high) {
            return;//завершить выполнение если уже нечего делить
        }
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        double opora = array[middle].getArea();

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i].getArea() < opora) {
                i++;
            }

            while (array[j].getArea() > opora) {
                j--;
            }

            if (i <= j) { //меняем местами
                Space temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }
    }

    public Space[] getSortArrayFlats() {// Метод получения отсортированного по убыванию площадей массива квартир
        int count = 0;
        for (Floor floor : floors) {
            Space[] flats = floor.getArrSpaces();
            for (Space flat : flats) {
                count++;
            }
        }
        Space[] arr = new Flat[count];
        for (Floor floor : floors) {
            Space[] flats = floor.getArrSpaces();
            for (int i = 0; i < flats.length; i++) {
                arr[i] = flats[i];
            }
        }
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }
}
