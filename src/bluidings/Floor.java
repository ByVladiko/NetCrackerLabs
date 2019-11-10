package bluidings;

import bluidings.DwellingBuilding.Flat;
import utils.OneWayList.OneWayList;

public interface Floor {
    public double getSumArea(); // получение общей площади помещений на этаже

    public Space getSpace(int numOffice); // получение помещения по его номеру
    
    public OneWayList<Space> getSpaces(); // Получение односвязного списка помещений этажа

    public int getSumRoomCount(); //	получение количества комнат на этаже

    public Space[] getArrSpaces(); // получение массива всех помещений этажа

    public void setSpace(int numOffice, Space newOffice); // изменение помещения по его номеру и ссылке на новое помещение

                                                            // вставки помещения по его номеру и ссылке на новое помещение
    
    public void removeAt(int index);  // удаление помещения по его номеру,

    public Space getBestSpace();  // получение лучшего помещения на этаже
    
    public int getSpaceCount(); // получение количества помещений на этаже

    public void setArrSpaces(Space[] newFloor);
}
