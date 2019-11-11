package netcrackerlab2;

import buildings.Buildings;
import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NetCrackerLab2 {

    public static void main(String[] args) {

        Space office = new Office(4, 25);
        Space office2 = new Office(5, 10);
        Space office3 = new Office(3, 30);
        Space office4 = new Office(2, 22);
        Space office5 = new Office(3, 10);
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

        OutputStreamWriter out = new OutputStreamWriter(System.out);
        Buildings.writeBuildingFormat(offBuild, out);
        try {
            out.write('\n');
            out.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }

        System.out.println(offFloor.toString());
        System.out.println(offBuild.toString());
    }
}
