package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class ApplicationsResponse {
    private List<ApplicationResponse> applications;

    // Конструктор используется Gson при чтении JSON.
    private ApplicationsResponse() {
    }

    public ApplicationsResponse(List<ApplicationResponse> applications) {
        this.applications = applications;
    }

    public List<ApplicationResponse> getApplications() {
        return applications;
    }
}
