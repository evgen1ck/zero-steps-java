package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class ApplicationRequest {
    private String title;
    private String description;
    private List<String> directions;
    private Long amount;

    // Конструктор используется Gson при чтении JSON.
    private ApplicationRequest() {
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

    public String getDescription() {
        return description;
    }

    public List<String> getDirections() {
        return directions;
    }

    public Long getAmount() {
        return amount;
    }
}
