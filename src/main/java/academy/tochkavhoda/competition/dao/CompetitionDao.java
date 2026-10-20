package academy.tochkavhoda.competition.dao;

import java.util.List;
import academy.tochkavhoda.competition.model.*;

public interface CompetitionDao {
    void initialize();
    CompetitionResult getResult();
    void saveResult(CompetitionResult result);
}
