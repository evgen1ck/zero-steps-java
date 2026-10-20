package academy.tochkavhoda.competition.database;

import java.util.*;
import academy.tochkavhoda.competition.model.*;

public class Database {
    private static final Database INSTANCE = new Database();
    private final Map<Integer, User> users = new LinkedHashMap<>();
    private final Map<String, Integer> sessions = new HashMap<>();
    private final Map<Integer, Application> applications = new LinkedHashMap<>();
    private final List<Rating> ratings = new ArrayList<>();
    private int nextUserId = 1;
    private int nextApplicationId = 1;
    private CompetitionResult result;
    private boolean initialized;

    private Database() { }
    public static Database getInstance() { return INSTANCE; }

    public void initialize() {
        if (initialized) { throw new IllegalStateException("Сервер уже создан"); }
        initialized = true;
    }

    public void clear() {
        users.clear(); sessions.clear(); applications.clear(); ratings.clear();
        nextUserId = 1; nextApplicationId = 1;
        result = null; initialized = false;
    }

    public int nextUserId() { return nextUserId++; }
    public int nextApplicationId() { return nextApplicationId++; }
    public void saveUser(User user) { users.put(user.getId(), user); }
    public List<User> getUsers() { return new ArrayList<>(users.values()); }
    public User getUserByLogin(String login) {
        for (User user : users.values()) {
            if (user.getLogin().equals(login)) { return user; }
        }
        return null;
    }
    public User getUserByToken(String token) { return users.get(sessions.get(token)); }
    public void addSession(String token, int userId) {
        removeSessions(userId);
        sessions.put(token, userId);
    }
    public void removeSession(String token) { sessions.remove(token); }
    private void removeSessions(int userId) {
        Iterator<Map.Entry<String, Integer>> iterator = sessions.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() == userId) { iterator.remove(); }
        }
    }
    public void removeUser(int userId) {
        users.remove(userId);
        removeSessions(userId);
        for (Application application : getApplications()) {
            if (application.getParticipantId() == userId) {
                removeApplication(application.getId());
            }
        }
        Iterator<Rating> iterator = ratings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getExpertId() == userId) { iterator.remove(); }
        }
    }
    public void saveApplication(Application application) { applications.put(application.getId(), application); }
    public Application getApplication(int id) { return applications.get(id); }
    public List<Application> getApplications() { return new ArrayList<>(applications.values()); }
    public void removeApplication(int id) {
        applications.remove(id);
        Iterator<Rating> iterator = ratings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getApplicationId() == id) { iterator.remove(); }
        }
    }
    public List<Rating> getRatings(int applicationId) {
        List<Rating> found = new ArrayList<>();
        for (Rating rating : ratings) {
            if (rating.getApplicationId() == applicationId) { found.add(rating); }
        }
        return found;
    }
    public Rating getRating(int expertId, int applicationId) {
        for (Rating rating : ratings) {
            if (rating.getExpertId() == expertId && rating.getApplicationId() == applicationId) { return rating; }
        }
        return null;
    }
    public void saveRating(Rating rating) {
        removeRating(rating.getExpertId(), rating.getApplicationId());
        ratings.add(rating);
    }
    public void removeRating(int expertId, int applicationId) {
        Iterator<Rating> iterator = ratings.iterator();
        while (iterator.hasNext()) {
            Rating rating = iterator.next();
            if (rating.getExpertId() == expertId && rating.getApplicationId() == applicationId) { iterator.remove(); }
        }
    }
    public CompetitionResult getResult() { return result; }
    public void saveResult(CompetitionResult result) { this.result = result; }
}
