package academy.tochkavhoda.competition.model;

import java.util.List;

public class CompetitionResult {
    private final List<ResultItem> ranking;
    private final long remainingFund;

    public CompetitionResult(List<ResultItem> ranking, long remainingFund) {
        this.ranking = java.util.Collections.unmodifiableList(new java.util.ArrayList<>(ranking));
        this.remainingFund = remainingFund;
    }

    public List<ResultItem> getRanking() {
        return ranking;
    }

    public long getRemainingFund() {
        return remainingFund;
    }
}
