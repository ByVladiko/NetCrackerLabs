package Tests;

import buildings.Buildings;
import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Run {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

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

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        Buildings.writeBuildingFormat(offBuild, osw);
        try {
            osw.write('\n');
            osw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(offFloor.toString());
        System.out.println(offBuild.toString());

        FileOutputStream fos = new FileOutputStream("temp.out");
        Buildings.serializeBuilding(offBuild, fos);

        File file = new File("temp.out");
        FileInputStream fis = new FileInputStream(file);

        Building offBuildDeser = new OfficeBuilding(Buildings.deserializeBuilding(fis).getFloors());

        System.out.println("Сериализованный: " + offBuildDeser.toString());
    }
}
