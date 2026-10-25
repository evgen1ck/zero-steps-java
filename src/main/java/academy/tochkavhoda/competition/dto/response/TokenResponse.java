package academy.tochkavhoda.competition.dto.response;

public class TokenResponse {
    private String token;

    // Конструктор используется Gson при чтении JSON.
    private TokenResponse() {
    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
