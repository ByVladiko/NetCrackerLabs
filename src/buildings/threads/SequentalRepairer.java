package buildings.threads;

import buildings.Interfaces.Floor;

public class SequentalRepairer implements Runnable {

    private Floor floor;
    private MySemaphore semaphore;

    public SequentalRepairer(Floor floor, MySemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }
    
    @Override
    public void run() {
        int index = 0, spaceCount = floor.getSpaceCount();
        while (!Thread.currentThread().isInterrupted() && index < spaceCount)
        {
            semaphore.action(index, floor.getSpace(index));
            semaphore.setNeedCleaning(true);
        }
    }
}
