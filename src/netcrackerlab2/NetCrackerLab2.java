package netcrackerlab2;

import bluidings.Office.Office;
import bluidings.Office.OfficeBuilding;
import bluidings.Office.OfficeFloor;
import bluidings.*;

public class NetCrackerLab2 {

    public static void main(String[] args) {

        Space office = new Office(25, 4);
        Space office2 = new Office(10, 5);
        Space office3 = new Office(30, 3);
        Space office4 = new Office(22, 2);
        Space office5 = new Office(10, 3);
        Space office6 = new Office(5, 5);

        Floor offFloor = new OfficeFloor(2);
        offFloor.setSpace(0, office);
        offFloor.setSpace(1, office2);

        Floor offFloor2 = new OfficeFloor(2);
        offFloor2.getSpaces().add(office3); // Добавляется еще один элемент и присваивается значение оффис3
        offFloor2.setSpace(1, office4);

        Floor offFloor3 = new OfficeFloor(2);
        offFloor3.setSpace(0, office5);
        offFloor3.setSpace(1, office6);

        Floor[] building = {offFloor, offFloor2, offFloor3};

        Building offBuild = new OfficeBuilding(building);

        System.out.println("Лучшие аппартаменты первого этажа имеют площадь: " + offFloor.getBestSpace().getArea());
        System.out.println("Лучшие аппартаменты здания имеют площадь: " + offBuild.getBestSpace().getArea());
        
        System.out.println(offBuild.getSpace(0).getArea());
        System.out.println(offBuild.getSpace(1).getArea());
        System.out.println(offBuild.getSpace(2).getArea());
        System.out.println(offBuild.getSpace(3).getArea());
        System.out.println(offBuild.getSpace(4).getArea());
        System.out.println(offBuild.getSpace(5).getArea());
        System.out.println(offBuild.getSpace(6).getArea());
    }
}
