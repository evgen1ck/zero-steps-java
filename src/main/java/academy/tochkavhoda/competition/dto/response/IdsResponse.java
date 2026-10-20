package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class IdsResponse {
    private List<Integer> applicationIds;

    public IdsResponse() {
    }

    public IdsResponse(List<Integer> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public List<Integer> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<Integer> applicationIds) {
        this.applicationIds = applicationIds;
    }
}
