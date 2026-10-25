package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class ResultsResponse {
    private List<ResultItemResponse> ranking;
    private long remainingFund;

    // Конструктор используется Gson при чтении JSON.
    private ResultsResponse() {
    }

    public ResultsResponse(List<ResultItemResponse> ranking, long remainingFund) {
        this.ranking = ranking;
        this.remainingFund = remainingFund;
    }

    public List<ResultItemResponse> getRanking() {
        return ranking;
    }

    public long getRemainingFund() {
        return remainingFund;
    }
}
