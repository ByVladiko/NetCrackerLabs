package Tests;

import buildings.Buildings;
import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.threads.Cleaner;
import buildings.threads.MySemaphore;
import buildings.threads.Repairer;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Run {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        Space office = new Office(4, 25);
        Space office2 = new Office(5, 10);
        Space office3 = new Office(3, 30);
        Space office4 = new Office(2, 22);
        Space office5 = new Office(3, 10.4);
        Space office6 = new Office(5, 5);

        Floor offFloor = new OfficeFloor(2);
        offFloor.setSpace(0, office);
        offFloor.setSpace(1, office2);
//        offFloor.setSpace(2, new Office(10, 15));
//        offFloor.setSpace(3, new Office(33, 33));

        Floor offFloor2 = new OfficeFloor(2);
        offFloor2.setSpace(0, office3);
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
        try {
            System.out.println(offBuild.getSpace(6).getArea());
        } catch (SpaceIndexOutOfBoundsException e) {
            System.err.println("Неверный ввод индекса помещения");
        }

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
        
        FileWriter out = new FileWriter(new File("building.txt"));
        Buildings.writeBuilding(offBuild, out);
        out.flush();
        out.close();

        File file = new File("temp.out");
        FileInputStream fis = new FileInputStream(file);

        Building offBuildDeser = new OfficeBuilding(Buildings.deserializeBuilding(fis).getFloors());

        System.out.println("Сериализованный: " + offBuildDeser.toString());

        for (Space floor : offFloor3.getArrSpaces()) {
            System.out.println(floor.getArea() + " " + floor.getRoomCount());
        }

        for (Floor floor : offBuild.getFloors()) {
            System.out.println(floor.getBestSpace());
        }

//        Repairer repairer = new Repairer(offFloor);
//        Cleaner cleaner = new Cleaner(offFloor);
//        repairer.start();
//        cleaner.start();

        System.out.println("--------------------------");

        for (int i = 0; i < offFloor.getSpaceCount(); i++) {
            System.out.println(offFloor.getSpace(i));
        }

        System.out.println("--------------------------");
        
        MySemaphore semaphor = new MySemaphore();
        Thread t1 = new Thread(new SequentalRepairer(semaphor, offFloor));
        Thread t2 = new Thread(new SequentalCleaner(semaphor, offFloor));

        t1.start();
        t2.start();
    }
}
