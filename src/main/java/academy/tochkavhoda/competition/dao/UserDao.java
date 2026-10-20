package academy.tochkavhoda.competition.dao;

import java.util.List;
import academy.tochkavhoda.competition.model.*;

public interface UserDao {
    int nextUserId();
    void saveUser(User user);
    List<User> getUsers();
    User getUserByLogin(String login);
    User getUserByToken(String token);
    void addSession(String token, int userId);
    void removeSession(String token);
    void removeUser(int userId);
}
