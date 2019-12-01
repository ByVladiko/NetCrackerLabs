package buildings;

import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import java.util.Iterator;

public class SynchronizedFloor implements Floor {

    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized double getSumArea() {
        return floor.getSumArea();
    }

    @Override
    public synchronized Space getSpace(int numOffice) {
        return floor.getSpace(numOffice);
    }

    @Override
    public synchronized int getSumRoomCount() {
        return floor.getSumRoomCount();
    }

    @Override
    public synchronized Space[] getArrSpaces() {
        return floor.getArrSpaces();
    }

    @Override
    public synchronized void setSpace(int numOffice, Space newOffice) {
        floor.setSpace(numOffice, newOffice);
    }

    @Override
    public synchronized void insertAt(int numOffice, Space newOffice) {
        floor.insertAt(numOffice, newOffice);
    }

    @Override
    public synchronized void removeAt(int index) {
        floor.removeAt(index);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized int getSpaceCount() {
        return floor.getSpaceCount();
    }

    @Override
    public synchronized void setArrSpaces(Space[] newFloor) {
        floor.setArrSpaces(newFloor);
    }

    @Override
    public synchronized Iterator iterator() {
        return floor.iterator();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return floor.clone();
    }

}
