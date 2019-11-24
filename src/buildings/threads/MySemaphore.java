package buildings.threads;

import buildings.Interfaces.Space;

public class MySemaphore {
    
    private boolean needCleaning;        

    public MySemaphore() {
    }
    
    public boolean isNeedCleaning() {
        return needCleaning;
    }

    public void setNeedCleaning(boolean needCleaning) {
        this.needCleaning = needCleaning;
    }
    
    public synchronized void action(int index, Space space)
    {
        while (!needCleaning)
        {
            try { wait(); }
            catch (InterruptedException e) { System.out.println(e.getMessage()); }
        }
//        System.out.println(String.format("Repairing space number %d with total area %.1f square metres", index, space.getArea()));
        notify();
    }
}
