package buildings.threads;

import buildings.Interfaces.Floor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SequentalCleaner implements Runnable {

    private MySemaphore semaphore;
    private Floor floor;

    public SequentalCleaner(MySemaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        synchronized (semaphore) {
            for (int i = 0; i < floor.getSpaceCount(); i++) {
                if (semaphore.isNeedCleaning()) {
                    try {
                        semaphore.notify();
                        Service.clean(i, floor.getSpace(i).getArea());
                        semaphore.setNeedCleaning(false);
                        semaphore.wait();
                        semaphore.notify();
                    } catch (InterruptedException ex) {
                        System.out.println("Cleaner has interrupted");
                    }
                }
            }
        }
        System.out.println("Cleaner has finished");
    }
}
