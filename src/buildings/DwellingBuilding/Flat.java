package buildings.DwellingBuilding;

import buildings.Interfaces.Space;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Flat implements Space, Serializable, Cloneable {

    public static final double AREA_CONST = 50;
    public static final int ROOM_COUNT_CONST = 2;

    private double area;
    private int roomCount;

    public Flat() {
        this.roomCount = ROOM_COUNT_CONST;
        this.area = AREA_CONST;
    }

    public Flat(double area) {
        this.area = area;
        this.roomCount = ROOM_COUNT_CONST;
    }

    public Flat(int roomCount, double area) {
        this.area = area;
        this.roomCount = roomCount;
    }

    public double getArea() {
        return area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return String.format("Flat (%d, %.1f)", roomCount, area);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof DwellingFloor)) {
            return false;
        }
        Flat space = (Flat) object;
        return area == space.getArea() && roomCount == space.getRoomCount();
    }

    @Override
    public int hashCode() {
        String binaryArea = Long.toBinaryString(Double.doubleToLongBits(area));
        String first4Bytes = binaryArea.substring(0, 31);
        String second4Bytes = binaryArea.substring(32);
        int k1 = Integer.parseInt(first4Bytes, 2);
        int k2 = Integer.parseInt(second4Bytes, 2);
        return (roomCount ^ k1) ^ k2;
    }

    @Override
    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Flat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int compareTo(Space flat) {
        if(getArea() < flat.getArea()) {
            return -1;
        }
        if(getArea() > flat.getArea()) {
            return 1;
        }
        return 0;
    }
}
