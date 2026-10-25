package academy.tochkavhoda.competition.model;

public class ResultItem {
    private final int applicationId;
    private final double average;
    private final long amount;
    private final boolean funded;

    public ResultItem(int applicationId, double average, long amount, boolean funded) {
        this.applicationId = applicationId;
        this.average = average;
        this.amount = amount;
        this.funded = funded;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public double getAverage() {
        return average;
    }

    public long getAmount() {
        return amount;
    }

    public boolean getFunded() {
        return funded;
    }
}
