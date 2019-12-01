package buildings.threads;

import buildings.Interfaces.Floor;

public class Repairer extends Thread {

    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < floor.getArrSpaces().length && !isInterrupted()) {
            Service.repair(i, floor.getSpace(i).getArea());
            i++;
        }
        if (isInterrupted()) {
            System.out.println("Repairer has interrupted");
        } else {
            System.out.println("Repairer has finished");
        }
    }
}
