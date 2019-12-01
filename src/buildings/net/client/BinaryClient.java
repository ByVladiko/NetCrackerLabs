package buildings.net.client;

import buildings.Buildings;
import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryClient {

    private Socket socket;
    private ObjectInputStream in; // поток чтения из сокета
    private ObjectOutputStream out; // поток чтения в сокет
    private File file;

    public static void main(String[] args) throws IOException {
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

        Writer outInfo = new FileWriter(new File("src\\buildings\\net\\server\\building.txt"));
        Buildings.writeBuilding(offBuild, outInfo);
        outInfo.flush();
        
        Writer outType = new FileWriter(new File("src\\buildings\\net\\server\\buildingType.txt"));
        Buildings.writeBuildingType(offBuild, outType);
        outType.flush();
        
        
        Socket clientSocket = new Socket("localhost", 8000);
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        
        
        
        clientSocket.close();
    }
}
