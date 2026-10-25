package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.UserDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.User;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Database database = Database.getInstance();

    @Override
    public int nextUserId() {
        return database.nextUserId();
    }

    @Override
    public void saveUser(User user) {
        database.saveUser(user);
    }

    @Override
    public List<User> getUsers() {
        return database.getUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return database.getUserByLogin(login);
    }

    @Override
    public User getUserByToken(String token) {
        return database.getUserByToken(token);
    }

    @Override
    public void addSession(String token, int userId) {
        database.addSession(token, userId);
    }

    @Override
    public void removeSession(String token) {
        database.removeSession(token);
    }

    @Override
    public void removeUser(int userId) {
        database.removeUser(userId);
    }
}
