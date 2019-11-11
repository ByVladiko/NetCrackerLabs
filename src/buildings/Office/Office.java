package buildings.Office;

import buildings.Interfaces.Space;
import java.io.Serializable;

public class Office implements Space, Serializable {

    public static final double AREA_CONST = 250;

    private double area;
    private int roomCount;

    public Office() { // Конструктор по умолчанию
        this.area = AREA_CONST;
    }

    public Office(double area) { // Конструктор может принимать площадь офиса 
        this.area = area;
        this.roomCount = 1;
    }

    public Office(int roomCount, double area) { // Конструктор может принимать площадь офиса и количество комнат
        this.area = area;
        this.roomCount = roomCount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public String toString() {
        return String.format("Office (%d, %.1f)", roomCount, area);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Office)) {
            return false;
        }
        Office space = (Office) object;
        return area == space.getArea() && roomCount == space.getRoomCount();
    }

    public int hashCode() {
        String binaryArea = Long.toBinaryString(Double.doubleToLongBits(area));
        String first4Bytes = binaryArea.substring(0, 31);
        String second4Bytes = binaryArea.substring(32);
        int k1 = Integer.parseInt(first4Bytes, 2);
        int k2 = Integer.parseInt(second4Bytes, 2);
        return (roomCount ^ k1) ^ k2;
    }

    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.getMessage();
        }
        return result;
    }
}
