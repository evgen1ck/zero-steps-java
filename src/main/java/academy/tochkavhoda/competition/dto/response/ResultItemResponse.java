package academy.tochkavhoda.competition.dto.response;

public class ResultItemResponse {
    private int applicationId;
    private double average;
    private long amount;
    private boolean funded;

    // Конструктор используется Gson при чтении JSON.
    private ResultItemResponse() {
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
