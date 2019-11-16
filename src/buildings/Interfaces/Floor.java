package buildings.Interfaces;

public interface Floor {

    public double getSumArea(); // Получение общей площади помещений на этаже

    public Space getSpace(int numOffice); // Получение помещения по его номеру

    public int getSumRoomCount(); // Получение количества комнат на этаже

    public Space[] getArrSpaces(); // Получение массива всех помещений этажа

    public void setSpace(int numOffice, Space newOffice); // Изменение помещения по его номеру и ссылке на новое помещение

    public void insertAt(int numOffice, Space newOffice);// Вставка помещения по его номеру и ссылке на новое помещение
    
    public void removeAt(int index);  // Удаление помещения по его номеру,

    public Space getBestSpace();  // Получение лучшего помещения на этаже

    public int getSpaceCount(); // Получение количества помещений на этаже

    public void setArrSpaces(Space[] newFloor); // Изменение этажа

    public Object clone(); // Клонирование этажа здания
}
