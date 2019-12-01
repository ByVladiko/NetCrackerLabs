package buildings.threads;

public class Service {

    static protected void clean(int index, double area) {
        System.out.println("Cleaning room number " + index + " with total area " + area + " square meters");
    }

    static protected void repair(int index, double area) {
        System.out.println("Repairing space number " + index + " with total area " + area + " square meters");
    }
}
