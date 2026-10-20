package academy.tochkavhoda.competition.dao;

import java.util.List;
import academy.tochkavhoda.competition.model.*;

public interface ApplicationDao {
    int nextApplicationId();
    void saveApplication(Application application);
    Application getApplication(int id);
    List<Application> getApplications();
    void removeApplication(int id);
    List<Rating> getRatings(int applicationId);
    Rating getRating(int expertId, int applicationId);
    void saveRating(Rating rating);
    void removeRating(int expertId, int applicationId);
}
