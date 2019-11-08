package bluidings;

public interface Building {
    public Floor[] getOfficeBuilding();
    
    public Floor getFloor(int index);
    
    public int getSumOffices();
    
    public double getSumArea();
    
    public int getSumRoomCount();
    
    public Space getOffice(int index);
    
    public void setSpace(int index, Space newOffice) ;
    
    public void insertSpace(int index, Space newOffice);
    
    public void removeAt(int index);
    
    public Space getBestSpace();
}
