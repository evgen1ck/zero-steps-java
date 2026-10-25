package academy.tochkavhoda.competition.dto.response;

public class ErrorResponse {
    private String error;

    // Конструктор используется Gson при чтении JSON.
    private ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
