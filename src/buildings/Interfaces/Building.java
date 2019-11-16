package buildings.Interfaces;

public interface Building {

    public Floor[] getFloors(); // Получение массива этажей здания

    public Floor getFloor(int index); // Получение этажа здания по его номеру

    public int getSumSpaces(); // Получение количества помещений в здании 

    public double getSumArea(); // Получение общей площади всех помещений здания

    public int getSumRoomCount(); // Получение общего количества комнат в помещениях здания

    public Space getSpace(int index); // Получение помещения по его номеру в здании

    public void setSpace(int index, Space newOffice); // Изменение помещения в здании по номеру и ссылке на новое помещение 

    public void insertAt(int index, Space newOffice); // Вставка помещения в здании по будущему номеру и ссылке на новое помещение

    public void removeAt(int index); // Удаление помещения из здания

    public Space getBestSpace(); // Получение лучшего помещения в здании

    public void setFloor(int index, Floor newOfficeFloor); // Изменение этажа по его номеру и ссылке на новый этаж

    public int getSumFloorCount(); // Получение количества этажей в здании

    public Object clone(); // Клонирование здания
}
