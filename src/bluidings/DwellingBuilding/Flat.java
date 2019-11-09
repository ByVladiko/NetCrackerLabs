package bluidings.DwellingBuilding;

import bluidings.Space;

public class Flat implements Space {
    public static final double AREA_CONST = 50;
    public static final int ROOM_COUNT_CONST = 2;
    
    private double area;
    private int roomCount;
    
    public Flat(){
        this.roomCount = ROOM_COUNT_CONST;
        this.area = AREA_CONST;
    }
    
    public Flat(double area) {
        this.area = area;
        this.roomCount = ROOM_COUNT_CONST;
    }
    
    public Flat(double area, int roomCount) {
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
}
