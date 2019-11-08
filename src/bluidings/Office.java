package bluidings;

public class Office implements Space {
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

    public Office(double area, int roomCount) { // Конструктор может принимать площадь офиса и количество комнат
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
}
