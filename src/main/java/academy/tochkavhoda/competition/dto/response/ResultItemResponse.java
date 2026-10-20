package academy.tochkavhoda.competition.dto.response;


public class ResultItemResponse {
    private int applicationId;
    private double average;
    private long amount;
    private boolean funded;

    public ResultItemResponse() {
    }

    public ResultItemResponse(int applicationId, double average, long amount, boolean funded) {
        this.applicationId = applicationId;
        this.average = average;
        this.amount = amount;
        this.funded = funded;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean getFunded() {
        return funded;
    }

    public void setFunded(boolean funded) {
        this.funded = funded;
    }
}
