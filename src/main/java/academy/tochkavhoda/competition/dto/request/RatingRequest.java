package academy.tochkavhoda.competition.dto.request;

public class RatingRequest {
    private Integer applicationId;
    private Integer value;

    // Конструктор используется Gson при чтении JSON.
    private RatingRequest() {
    }

    public RatingRequest(Integer applicationId, Integer value) {
        this.applicationId = applicationId;
        this.value = value;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public Integer getValue() {
        return value;
    }
}
