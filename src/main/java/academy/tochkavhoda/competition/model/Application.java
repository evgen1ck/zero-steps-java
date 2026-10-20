package academy.tochkavhoda.competition.model;

import java.util.List;

public class Application {
    private final int id;
    private final int participantId;
    private final String title;
    private final String description;
    private final List<String> directions;
    private final long amount;

    public Application(int id, int participantId, String title, String description, List<String> directions, long amount) {
        this.id = id;
        this.participantId = participantId;
        this.title = title;
        this.description = description;
        this.directions = java.util.Collections.unmodifiableList(new java.util.ArrayList<>(directions));
        this.amount = amount;
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
}
