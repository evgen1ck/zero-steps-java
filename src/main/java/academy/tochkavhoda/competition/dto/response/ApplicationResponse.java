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

    public ApplicationResponse() {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
