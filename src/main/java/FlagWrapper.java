public class FlagWrapper {
    private boolean status;

    public FlagWrapper(boolean initialStatus) {
        this.status = initialStatus;
    }

    public void setStatus(boolean newStatus) {
        this.status = newStatus;
    }

    public boolean getStatus() {
        return this.status;
    }
}
