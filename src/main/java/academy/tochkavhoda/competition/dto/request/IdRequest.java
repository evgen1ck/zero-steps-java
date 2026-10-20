package academy.tochkavhoda.competition.dto.request;


public class IdRequest {
    private Integer applicationId;

    public IdRequest() {
    }

    public IdRequest(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
}
