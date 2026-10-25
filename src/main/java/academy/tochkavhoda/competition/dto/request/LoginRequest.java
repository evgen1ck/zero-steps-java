package academy.tochkavhoda.competition.dto.request;

public class LoginRequest {
    private String login;
    private String password;

    // Конструктор используется Gson при чтении JSON.
    private LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
