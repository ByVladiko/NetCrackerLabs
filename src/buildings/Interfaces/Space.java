package buildings.Interfaces;

public interface Space {

    public double getArea();

    public void setArea(double area);
    
    public int getRoomCount();
    
    public void setRoomCount(int roomCount);
    
    public Object clone();
}
