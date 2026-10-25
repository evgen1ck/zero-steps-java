package academy.tochkavhoda.competition.dto.request;

public class IdRequest {
    private Integer applicationId;

    // Конструктор используется Gson при чтении JSON.
    private IdRequest() {
    }

    public IdRequest(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }
}
