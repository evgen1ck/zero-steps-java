package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class ApplicationsRequest {
    private List<ApplicationRequest> applications;

    // Конструктор используется Gson при чтении JSON.
    private ApplicationsRequest() {
    }

    public ApplicationsRequest(List<ApplicationRequest> applications) {
        this.applications = applications;
    }

    public List<ApplicationRequest> getApplications() {
        return applications;
    }
}
