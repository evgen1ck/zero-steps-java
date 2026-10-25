package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class DirectionsRequest {
    private List<String> directions;

    // Конструктор используется Gson при чтении JSON.
    private DirectionsRequest() {
    }

    public DirectionsRequest(List<String> directions) {
        this.directions = directions;
    }

    public List<String> getDirections() {
        return directions;
    }
}
