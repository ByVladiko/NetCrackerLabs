package buildings.DwellingBuilding;

import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.OneWayList.OneWayList;

public class DwellingFloor implements Floor {

    private Space[] flats;

    public DwellingFloor(int flatCount) { // Конструктор может принимать количество квартир на этаже
        this.flats = new Flat[flatCount];
        for (int i = 0; i < flatCount; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) { // Конструктор может принимать массив квартир этажа
        this.flats = flats;
    }

    // Геттеры и сеттеры
    public Space[] getArrSpaces() { // Метод получения массива квартир этажа
        return flats;
    }

    public void setArrSpaces(Space[] flats) {
        this.flats = flats;
    }

    public int getSpaceCount() {
        return flats.length;
    }

    public double getSumArea() { // Метод получения общей площади квартир этажа
        double SumArea = 0;
        for (Space flat : flats) {
            SumArea = SumArea + flat.getArea() * flat.getRoomCount();
        }
        return SumArea;
    }

    public int getSumRoomCount() { // Метод получения общего количества комнат этажа
        int SumRoomCount = 0;
        for (Space flat : flats) {
            SumRoomCount = SumRoomCount + flat.getRoomCount();
        }
        return SumRoomCount;
    }

    public Space getSpace(int flatCount) { // Метод получения объекта квартиры по ее номеру на этаже
        return flats[flatCount];
    }

    public void setSpace(int flatCount, Space newFlat) { // Метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
        this.flats[flatCount] = (Flat) newFlat;
    }

    public void addFlat(int flatCount, Space newFlat) { // Метод добавления новой квартиры на этаже по будущему номеру квартиры
        if (flatCount < flats.length) {
            return;
        }
        Space[] newFlats = new Flat[flats.length + (flatCount - flats.length + 1)];
        for (int i = 0; i < flats.length; i++) {
            newFlats[i] = flats[i];
        }
        newFlats[flatCount] = (Flat) newFlat;
        setArrSpaces(newFlats);
    }

    public void removeAt(int flatCount) { // Метод удаления квартиры по ее номеру на этаже
        Space[] newFlats = new Flat[flats.length - 1];
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
    public OneWayList<Space> getSpaces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("DwellingFloor (" + getSpaceCount() + " ");
        for (int i = 0; i < flats.length; i++) {
            sb.append(flats[i].toString());
        }
        return sb.toString();
    }

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

    public int hashCode() {
        int result = getSpaceCount();
        for (Space space : flats) {
            result = result ^ space.hashCode();
        }
        return result;
    }
    
    public Object clone(){
        Floor result = null;
        try {
            result = (Floor) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.getMessage();
        }
        for (int i = 0; i < result.getSpaceCount(); i++) {
            result.setSpace(i, (Space) result.getSpace(i).clone());
        }
        return result;
    }
}
