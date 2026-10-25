package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.CompetitionResult;

public class CompetitionDaoImpl implements CompetitionDao {
    private final Database database = Database.getInstance();

    @Override
    public void initialize() {
        database.initialize();
    }

    @Override
    public CompetitionResult getResult() {
        return database.getResult();
    }

    @Override
    public void saveResult(CompetitionResult result) {
        database.saveResult(result);
    }
}
