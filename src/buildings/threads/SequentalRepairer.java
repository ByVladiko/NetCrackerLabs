package buildings.threads;

import buildings.Interfaces.Floor;

public class SequentalRepairer implements Runnable {

    private MySemaphore semaphore;
    private Floor floor;

    public SequentalRepairer(MySemaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        synchronized (semaphore) {
            for (int i = 0; i < floor.getSpaceCount(); i++) {
                if (!semaphore.isNeedCleaning()) {
                    try {
                        semaphore.notify();
                        Service.repair(i, floor.getSpace(i).getArea());
                        semaphore.setNeedCleaning(true);
                        semaphore.wait();
                        semaphore.notify();
                    } catch (InterruptedException ex) {
                        System.out.println("Repairer has interrupted");
                    }
                }
            }
        }
        System.out.println("Repairer has finished");
    }
}
