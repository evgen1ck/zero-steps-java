package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class ExpertRequest {
    private String firstName;
    private String lastName;
    private List<String> directions;
    private String login;
    private String password;

    // Конструктор используется Gson при чтении JSON.
    private ExpertRequest() {
    }

    public ExpertRequest(String firstName, String lastName, List<String> directions, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.directions = directions;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getDirections() {
        return directions;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
