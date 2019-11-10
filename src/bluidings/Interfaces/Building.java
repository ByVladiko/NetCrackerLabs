package bluidings.Interfaces;

public interface Building {
    public Floor[] getFloors();
    
    public Floor getFloor(int index);
    
    public int getSumSpaces();
    
    public double getSumArea();
    
    public int getSumRoomCount();
    
    public Space getSpace(int index);
    
    public void setSpace(int index, Space newOffice) ;
    
    public void insertAt(int index, Space newOffice);
    
    public void removeAt(int index);
    
    public Space getBestSpace();
    
    public void setFloor(int index, Floor newOfficeFloor);
    
    public int getSumFloorCount();
}
