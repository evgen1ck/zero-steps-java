package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class IdsResponse {
    private List<Integer> applicationIds;

    // Конструктор используется Gson при чтении JSON.
    private IdsResponse() {
    }

    public IdsResponse(List<Integer> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public List<Integer> getApplicationIds() {
        return applicationIds;
    }
}
