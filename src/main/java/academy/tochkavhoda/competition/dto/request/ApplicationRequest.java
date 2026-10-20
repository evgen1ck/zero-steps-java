package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class ApplicationRequest {
    private String title;
    private String description;
    private List<String> directions;
    private Long amount;

    public ApplicationRequest() {
    }

    public ApplicationRequest(String title, String description, List<String> directions, Long amount) {
        this.title = title;
        this.description = description;
        this.directions = directions;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
