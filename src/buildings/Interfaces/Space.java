package buildings.Interfaces;

public interface Space {

    public double getArea(); // Получение площади

    public void setArea(double area); // Изменение площади

    public int getRoomCount(); // Получение  количества комнат

    public void setRoomCount(int roomCount); // Изменение количества комнат

    public Object clone(); // Клонирование помещения
}
