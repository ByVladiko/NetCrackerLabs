package buildings.threads;

import buildings.Interfaces.Floor;

public class Cleaner extends Thread {
    
    private Floor floor;
    
    public Cleaner(Floor floor) {
        this.floor = floor;
    }
    
    @Override
    public void run() {
        int i = 0;
        while (i < floor.getArrSpaces().length && !isInterrupted()) {
            Service.clean(i, floor.getSpace(i).getArea());
            i++;
//            if (i == 1) {
//                interrupt(); // Тест прерывания потока
//            }
        }
        if (isInterrupted()) {
            System.out.println("Cleaner has interrupted");
        } else {
            System.out.println("Cleaner has finished");
        }
    }
}
