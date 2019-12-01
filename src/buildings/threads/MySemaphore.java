package buildings.threads;

public class MySemaphore {

    private boolean needCleaning;

    public MySemaphore() {
        needCleaning = false;
    }

    protected boolean isNeedCleaning() {
        return needCleaning;
    }

    protected void setNeedCleaning(boolean needCleaning) {
        this.needCleaning = needCleaning;
    }
}
