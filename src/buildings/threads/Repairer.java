package buildings.threads;

import buildings.Interfaces.Floor;

public class Repairer extends Thread  {
    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < floor.getArrSpaces().length; i++) {
            if (isInterrupted()) {
                System.out.println("Repairer has interrupted");
                return;
            }
            System.out.println("Repairing room number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
        }
        System.out.println("Repairer has finished");
    }
}
