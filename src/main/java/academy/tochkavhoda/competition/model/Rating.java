package academy.tochkavhoda.competition.model;


public class Rating {
    private final int expertId;
    private final int applicationId;
    private final int value;

    public Rating(int expertId, int applicationId, int value) {
        this.expertId = expertId;
        this.applicationId = applicationId;
        this.value = value;
    }

    public int getExpertId() {
        return expertId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public int getValue() {
        return value;
    }
}
