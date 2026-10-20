package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class ResultsResponse {
    private List<ResultItemResponse> ranking;
    private long remainingFund;

    public ResultsResponse() {
    }

    public ResultsResponse(List<ResultItemResponse> ranking, long remainingFund) {
        this.ranking = ranking;
        this.remainingFund = remainingFund;
    }

    public List<ResultItemResponse> getRanking() {
        return ranking;
    }

    public void setRanking(List<ResultItemResponse> ranking) {
        this.ranking = ranking;
    }

    public long getRemainingFund() {
        return remainingFund;
    }

    public void setRemainingFund(long remainingFund) {
        this.remainingFund = remainingFund;
    }
}
