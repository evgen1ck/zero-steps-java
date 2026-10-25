package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.CompetitionResult;

public interface CompetitionDao {
    void initialize();
    CompetitionResult getResult();
    void saveResult(CompetitionResult result);
}
