package academy.tochkavhoda.competition.daoimpl;

import java.util.List;
import academy.tochkavhoda.competition.model.*;
import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.database.Database;

public class ApplicationDaoImpl implements ApplicationDao {
    private final Database database = Database.getInstance();

    @Override
    public int nextApplicationId() {
        return database.nextApplicationId();
    }

    @Override
    public void saveApplication(Application application) {
        database.saveApplication(application);
    }

    @Override
    public Application getApplication(int id) {
        return database.getApplication(id);
    }

    @Override
    public List<Application> getApplications() {
        return database.getApplications();
    }

    @Override
    public void removeApplication(int id) {
        database.removeApplication(id);
    }

    @Override
    public List<Rating> getRatings(int applicationId) {
        return database.getRatings(applicationId);
    }

    @Override
    public Rating getRating(int expertId, int applicationId) {
        return database.getRating(expertId, applicationId);
    }

    @Override
    public void saveRating(Rating rating) {
        database.saveRating(rating);
    }

    @Override
    public void removeRating(int expertId, int applicationId) {
        database.removeRating(expertId, applicationId);
    }
}
