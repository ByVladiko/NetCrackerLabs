package buildings;

import buildings.Interfaces.Floor;
import buildings.Interfaces.Building;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import java.io.*;
import java.util.Locale;

public class Buildings {
    
    public static void outputBuilding (Building building, OutputStream out) throws IOException { // Запись данных о здании в байтовый поток 
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(building.getSumFloorCount());
        for (int i = 0; i < building.getSumFloorCount(); i++) {
            dos.writeInt(building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                dos.writeInt(building.getFloor(i).getSpace(i).getRoomCount());
                dos.writeDouble(building.getFloor(i).getSpace(i).getArea());
            }
        }
        dos.close();
    }
    
    public static Building inputBuilding (InputStream in) throws IOException { //Чтение данных о здании из байтового потока 
        DataInputStream dis = new DataInputStream(in);
        Floor[] floors = new Floor[dis.readInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[dis.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = new Office(dis.readInt(), dis.readDouble());
            }
        }
        dis.close();
        return new OfficeBuilding(floors);
    }
    
    public static void writeBuilding (Building building, Writer out) { // Запись здания в символьный поток 
        PrintWriter pw = new PrintWriter(out);
        pw.print(building.getSumFloorCount() + " ");
        for (int i = 0; i < building.getSumFloorCount(); i++) {
            pw.print(building.getFloor(i).getSpaceCount() + " ");
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                pw.print(building.getFloor(i).getSpace(i).getRoomCount() + " ");
                pw.print(building.getFloor(i).getSpace(i).getArea() + " ");
            }
        }
        pw.close();
    }
    
    public static Building readBuilding (Reader in) throws IOException { // Чтение здания из символьного потока 
        StreamTokenizer st = new StreamTokenizer(in);
        Floor[] floors = new Floor[st.nextToken()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[st.nextToken()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = new Office(st.nextToken(), st.nextToken());
            }
        }
        return new OfficeBuilding(floors);
    }
    
    public static void serializeBuilding (Building building, OutputStream out) throws IOException { // Сериализация здания в байтовый поток
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
        oos.close();
    }
    
    public static Building deserializeBuilding (InputStream in) throws IOException, ClassNotFoundException { // Десериализация здания из байтового потока
        ObjectInputStream ois = new ObjectInputStream(in);
        ois.close();
        return (Building) ois.readObject();
    }
    
    public static void writeBuildingFormat (Building building, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        pw.printf("%d ", building.getSumFloorCount());
        for (int i = 0; i < building.getSumFloorCount(); i++) {
            pw.printf("%d ", building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                pw.printf("%d ", building.getFloor(i).getSpace(j).getRoomCount());
                pw.printf("%.1f ", building.getFloor(i).getSpace(j).getArea());
            }
        }
    }
}
