package buildings.net.server;

import Factory.DwellingFactory;
import Factory.HotelFactory;
import Factory.OfficeFactory;
import buildings.BuildingUnderArrestException;
import buildings.Buildings;
import buildings.DwellingBuilding.Hotel.Hotel;
import buildings.Interfaces.Building;
import buildings.Interfaces.BuildingFactory;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {

    private static double Cost(Building building) throws BuildingUnderArrestException {
        if (isUnderArrest())
            throw new BuildingUnderArrestException();
        if (building instanceof OfficeBuilding) {
            return 1500 * building.getSumArea();
        }
        if (building instanceof Hotel) {
            return 2000 * building.getSumArea();
        }
        return 1000 * building.getSumArea();
    }
    
    private static boolean isUnderArrest() {
        return (new Random().nextInt(100)) < 10;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted");
            try {
                OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
                writer.write("connected");
                writer.flush();
                writer.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (true) {
                    String type = reader.readLine();
                    switch (type) {
                        case "Dwelling": 
                            Buildings.setBuildingFactory(new DwellingFactory());
                        case "OfficeBuilding": 
                            Buildings.setBuildingFactory(new OfficeFactory());
                        case "Hotel": 
                            Buildings.setBuildingFactory(new HotelFactory());
                        default: 
                            System.out.println("Incorrect type");
                            
                    }
                    
                    }
                }

//            writer.write("java");
//            writer.flush();
//            writer.close();
                clientSocket.close();
            }
        }
    }
