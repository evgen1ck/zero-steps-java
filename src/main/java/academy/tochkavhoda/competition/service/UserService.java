package academy.tochkavhoda.competition.service;

import java.util.*;
import academy.tochkavhoda.competition.dao.*;
import academy.tochkavhoda.competition.daoimpl.*;
import academy.tochkavhoda.competition.model.*;
import academy.tochkavhoda.competition.dto.request.*;
import academy.tochkavhoda.competition.dto.response.*;
import academy.tochkavhoda.competition.exception.CompetitionException;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();
    private final CompetitionService competitionService;

    public UserService(CompetitionService competitionService) { this.competitionService = competitionService; }

    public ServerResponse registerParticipant(String json) {
        try {
            ParticipantRequest request = JsonService.read(json, ParticipantRequest.class);
            String firstName = Validation.text(request.getFirstName(), "firstName");
            String lastName = Validation.text(request.getLastName(), "lastName");
            String company = Validation.text(request.getCompany(), "company");
            String login = Validation.login(request.getLogin());
            String password = Validation.password(request.getPassword());
            checkLogin(login);
            for (User user : userDao.getUsers()) {
                if (user.getRole() == Role.PARTICIPANT && user.getCompany().equalsIgnoreCase(company)) {
                    throw new CompetitionException("Фирма уже зарегистрирована");
                }
            }
            userDao.saveUser(new User(userDao.nextUserId(), firstName, lastName, company,
                    login, password, Role.PARTICIPANT, new ArrayList<String>()));
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse registerExpert(String json) {
        try {
            ExpertRequest request = JsonService.read(json, ExpertRequest.class);
            String firstName = Validation.text(request.getFirstName(), "firstName");
            String lastName = Validation.text(request.getLastName(), "lastName");
            String login = Validation.login(request.getLogin());
            String password = Validation.password(request.getPassword());
            List<String> directions = Validation.directions(request.getDirections());
            checkLogin(login);
            for (User user : userDao.getUsers()) {
                if (user.getRole() == Role.EXPERT && user.getFirstName().equalsIgnoreCase(firstName)
                        && user.getLastName().equalsIgnoreCase(lastName)) {
                    throw new CompetitionException("Эксперт уже зарегистрирован");
                }
            }
            userDao.saveUser(new User(userDao.nextUserId(), firstName, lastName, null,
                    login, password, Role.EXPERT, directions));
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    private void checkLogin(String login) {
        if (userDao.getUserByLogin(login) != null) { throw new CompetitionException("Логин уже занят"); }
    }
    public ServerResponse login(String json) {
        try {
            LoginRequest request = JsonService.read(json, LoginRequest.class);
            String login = Validation.login(request.getLogin());
            String password = Validation.password(request.getPassword());
            User user = userDao.getUserByLogin(login);
            if (user == null || !user.getPassword().equals(password)) {
                throw new CompetitionException("Неверный логин или пароль");
            }
            String token = UUID.randomUUID().toString();
            userDao.addSession(token, user.getId());
            return JsonService.ok(new TokenResponse(token));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public User requireUser(String token) {
        User user = userDao.getUserByToken(token);
        if (user == null) { throw new CompetitionException("Недействительный токен"); }
        return user;
    }
    public User requireRole(String token, Role role) {
        User user = requireUser(token);
        if (user.getRole() != role) { throw new CompetitionException("Операция недоступна этой роли"); }
        return user;
    }
    public ServerResponse logout(String token) {
        try {
            requireUser(token);
            userDao.removeSession(token);
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse deleteAccount(String token) {
        try {
            User user = requireUser(token);
            competitionService.finishIfNeeded();
            userDao.removeUser(user.getId());
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
}
