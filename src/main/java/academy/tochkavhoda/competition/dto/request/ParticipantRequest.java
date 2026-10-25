package academy.tochkavhoda.competition.dto.request;

public class ParticipantRequest {
    private String firstName;
    private String lastName;
    private String company;
    private String login;
    private String password;

    // Конструктор используется Gson при чтении JSON.
    private ParticipantRequest() {
    }

    public ParticipantRequest(String firstName, String lastName, String company, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
