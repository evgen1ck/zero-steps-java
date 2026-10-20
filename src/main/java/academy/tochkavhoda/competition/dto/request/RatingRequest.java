package academy.tochkavhoda.competition.dto.request;


public class RatingRequest {
    private Integer applicationId;
    private Integer value;

    public RatingRequest() {
    }

    public RatingRequest(Integer applicationId, Integer value) {
        this.applicationId = applicationId;
        this.value = value;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
