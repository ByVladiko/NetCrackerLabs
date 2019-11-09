package bluidings;

import java.io.*;

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
    }
    
    public static Building inputBuilding (InputStream in) throws IOException { //Чтение данных о здании из байтового потока 
        DataInputStream dis = new DataInputStream(in);
        Floor[] floors = new Floor[dis.readInt()];
        
    }
    
    public static void writeBuilding (Building building, Writer out) { // Запись здания в символьный поток 
        
    }
    
    public static Building readBuilding (Reader in) { // Чтение здания из символьного потока 
        
    }
}
