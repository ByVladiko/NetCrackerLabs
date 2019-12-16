package buildings;

import Factory.DwellingFactory;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Building;
import buildings.Interfaces.BuildingFactory;
import buildings.Interfaces.Space;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class Buildings {

    private static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory bf) {
        factory = bf;
    }

    public static Space createSpace(double area) {
        return factory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area) {
        return factory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }

    public static Floor createFloor(int spaceCount) {
        return factory.createFloor(spaceCount);
    }

    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return factory.createBuilding(floorsCount, spacesCounts);
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException { // Запись данных о здании в байтовый поток 
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

    public static Building inputBuilding(InputStream in) throws IOException { //Чтение данных о здании из байтового потока 
        DataInputStream dis = new DataInputStream(in);
        Floor[] floors = new Floor[dis.readInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[dis.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = createSpace(dis.readInt(), dis.readDouble());
            }
            floors[i] = createFloor(spaces);
        }
        return factory.createBuilding(floors);
    }

    public static Building inputBuilding(InputStream in, Class buildingClass, Class floorClass, Class spaceClass) throws IOException {
        DataInputStream input = new DataInputStream(in);
        int floorCount = input.readInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            int spaceCount = input.readInt();
            floors[i] = createFloor(spaceCount, floorClass);
            for (int j = 0; j < spaceCount; j++) {
                floors[i].setSpace(j, createSpace(input.readInt(), input.readDouble(), spaceClass));
            }
        }
        return createBuilding(buildingClass, floors);
    }

    public static void writeBuilding(Building building, Writer out) { // Запись здания в символьный поток 
        PrintWriter pw = new PrintWriter(out);
        pw.print(building.getSumFloorCount() + " ");
        for (int i = 0; i < building.getSumFloorCount(); i++) {
            pw.print(building.getFloor(i).getSpaceCount() + " ");
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                pw.print(building.getFloor(i).getSpace(j).getRoomCount() + " ");
                pw.print(building.getFloor(i).getSpace(j).getArea() + " ");
            }
        }
    }

    public static double nextToken(StreamTokenizer st) throws IOException {
        st.nextToken();
        return st.nval;
    }

    public static Building readBuilding(Reader in) throws IOException { // Чтение здания из символьного потока 
        StreamTokenizer st = new StreamTokenizer(in);
        Floor[] floors = new Floor[(int) nextToken(st)];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[(int) nextToken(st)];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = createSpace((int) nextToken(st), (double) nextToken(st));
            }
            floors[i] = createFloor(spaces);
        }
        return factory.createBuilding(floors);
    }

    public static Building readBuilding(Reader in, Class<Building> buildingClass, Class<Floor> floorClass, Class<Space> spaceClass) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        int floorCount = (int) nextToken(st);
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            int spaceCount = (int) nextToken(st);
            floors[i] = createFloor(spaceCount, floorClass);
            for (int j = 0; j < spaceCount; j++) {
                int roomsCount = (int) nextToken(st);
                double area = (double) nextToken(st);
                floors[i].setSpace(j, createSpace(roomsCount, area, spaceClass));
            }
        }
        return createBuilding(buildingClass, floors);
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException { // Сериализация здания в байтовый поток
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException { // Десериализация здания из байтового потока
        ObjectInputStream ois = new ObjectInputStream(in);
        return (Building) ois.readObject();
    }

    public static void writeBuildingFormat(Building building, Writer out) {
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

    public static Space createSpace(double area, Class<Space> spaceClass) {
        try {
            Constructor<Space> constructor = spaceClass.getConstructor(double.class);
            return constructor.newInstance(area);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }

    }

    public static Space createSpace(int roomCount, double area, Class<Space> spaceClass) {
        try {
            Constructor<Space> constructor = spaceClass.getConstructor(double.class);
            return constructor.newInstance(roomCount, area);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(int spacesCount, Class<Floor> floorClass) {
        try {
            Constructor<Floor> constructor = floorClass.getConstructor(int.class);
            return constructor.newInstance(spacesCount);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(Class<Floor> floorClass, Space... spaces) {
        try {
            Constructor<Floor> constructor = floorClass.getConstructor(Space[].class);
            return constructor.newInstance((Object) spaces);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Building createBuilding(Class<Building> buildingClass, int... spacesCount) {
        try {
            Constructor<Building> constructor = buildingClass.getConstructor(int[].class);
            return constructor.newInstance((Object) spacesCount);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Building createBuilding(Class<Building> buildingClass, Floor... floors) {
        try {
            Constructor<Building> constructor = buildingClass.getConstructor(Floor[].class);
            return constructor.newInstance((Object) floors);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void writeBuildingType(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        pw.println(building.getType());
    }

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    public static <T> void sort(T[] objects, Comparator<T> comparator) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] objects) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[j].compareTo(objects[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }
}
