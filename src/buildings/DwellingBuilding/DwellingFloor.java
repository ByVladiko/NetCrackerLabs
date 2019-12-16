package buildings.DwellingBuilding;

import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Floor> {

    protected Space[] flats;

    public DwellingFloor(int flatCount) { // Конструктор может принимать количество квартир на этаже
        this.flats = new Flat[flatCount];
        for (int i = 0; i < flatCount; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flats) { // Конструктор может принимать массив квартир этажа
        this.flats = flats;
    }

    // Геттеры и сеттеры
    @Override
    public Space[] getArrSpaces() { // Метод получения массива квартир этажа
        return flats;
    }

    @Override
    public void setArrSpaces(Space[] flats) {
        this.flats = flats;
    }

    @Override
    public int getSpaceCount() {
        return flats.length;
    }

    @Override
    public double getSumArea() { // Метод получения общей площади квартир этажа
        double SumArea = 0;
        for (Space flat : flats) {
            SumArea = SumArea + flat.getArea() * flat.getRoomCount();
        }
        return SumArea;
    }

    @Override
    public int getSumRoomCount() { // Метод получения общего количества комнат этажа
        int SumRoomCount = 0;
        for (Space flat : flats) {
            SumRoomCount = SumRoomCount + flat.getRoomCount();
        }
        return SumRoomCount;
    }

    @Override
    public Space getSpace(int flatCount) { // Метод получения объекта квартиры по ее номеру на этаже
        return flats[flatCount];
    }

    @Override
    public void setSpace(int flatCount, Space newFlat) { // Метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
        this.flats[flatCount] = (Flat) newFlat;
    }

    @Override
    public void insertAt(int flatCount, Space newFlat) { // Метод добавления новой квартиры на этаже по будущему номеру квартиры
        if (flatCount < flats.length) {
            return;
        }
        Space[] newFlats = new Space[flats.length + (flatCount - flats.length + 1)];
        for (int i = 0; i < flats.length; i++) {
            newFlats[i] = flats[i];
        }
        newFlats[flatCount] = (Space) newFlat;
        setArrSpaces(newFlats);
    }

    @Override
    public void removeAt(int flatCount) { // Метод удаления квартиры по ее номеру на этаже
        Space[] newFlats = new Space[flats.length - 1];
        int j = 0;
        for (int i = 0; i < flats.length; i++) {
            if (i == flatCount) {
                continue;
            }
            newFlats[i] = flats[j];
            j++;
        }
        setArrSpaces(newFlats);
    }

    @Override
    public Space getBestSpace() { // Метод получения самой большой по площади квартиры этажа
        Space bestFlatSpace = null;
        for (Space flat : flats) {
            if (flat.getArea() > bestFlatSpace.getArea()) {
                bestFlatSpace = flat;
            }
        }
        return bestFlatSpace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DwellingFloor (" + getSpaceCount() + " ");
        for (Space flat : flats) {
            sb.append(flat.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof DwellingFloor)) {
            return false;
        }
        DwellingFloor floor = (DwellingFloor) object;
        for (int i = 0; i < floor.getSpaceCount(); i++) {
            if (flats[i].equals(floor.getSpace(i)) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = getSpaceCount();
        for (Space space : flats) {
            result = result ^ space.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DwellingFloor result = null;
        try {
            result = (DwellingFloor) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DwellingFloor.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.flats = flats.clone();
        for (int i = 0; i < result.getSpaceCount(); i++) {
            result.setSpace(i, (Space) result.getSpace(i).clone());
        }
        return result;
    }

    @Override
    public int compareTo(Floor floor) {
        if(getSpaceCount() < floor.getSpaceCount()) {
            return -1;
        }
        if(getSpaceCount() > floor.getSpaceCount()) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public Iterator iterator() {
        return new FloorIterator(this);
    }
    
    private class FloorIterator implements Iterator {

        DwellingFloor floor;
        
        private int index;
        
        public FloorIterator(DwellingFloor floor)
        {
            this.floor = floor;
            index = -1;
        }
        
        @Override
        public boolean hasNext() {
            if(index < flats.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return flats[index++];
        }
    }
}
