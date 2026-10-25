package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class ApplicationResponse {
    private int id;
    private int participantId;
    private String title;
    private String description;
    private List<String> directions;
    private long amount;
    private double average;
    private int ratingCount;

    // Конструктор используется Gson при чтении JSON.
    private ApplicationResponse() {
    }

    public ApplicationResponse(int id, int participantId, String title, String description, List<String> directions, long amount, double average, int ratingCount) {
        this.id = id;
        this.participantId = participantId;
        this.title = title;
        this.description = description;
        this.directions = directions;
        this.amount = amount;
        this.average = average;
        this.ratingCount = ratingCount;
    }

    public int getId() {
        return id;
    }

    public int getParticipantId() {
        return participantId;
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

    public long getAmount() {
        return amount;
    }

    public double getAverage() {
        return average;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
