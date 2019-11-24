package buildings.threads;

import buildings.Interfaces.Floor;

public class Cleaner extends Thread {

    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < floor.getArrSpaces().length; i++) {
            if (isInterrupted()) {
                System.out.println("Cleaner has interrupted");
                return;
            }
            System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
        }
        System.out.println("Cleaner has finished");
    }
}
