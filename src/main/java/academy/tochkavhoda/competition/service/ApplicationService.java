package academy.tochkavhoda.competition.service;

import java.util.*;
import academy.tochkavhoda.competition.dao.*;
import academy.tochkavhoda.competition.daoimpl.*;
import academy.tochkavhoda.competition.model.*;
import academy.tochkavhoda.competition.dto.request.*;
import academy.tochkavhoda.competition.dto.response.*;
import academy.tochkavhoda.competition.exception.CompetitionException;

public class ApplicationService {
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final UserService userService;
    private final CompetitionService competitionService;

    public ApplicationService(UserService userService, CompetitionService competitionService) {
        this.userService = userService; this.competitionService = competitionService;
    }
    public ServerResponse addApplication(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.PARTICIPANT);
            competitionService.requireOpen();
            ApplicationRequest request = JsonService.read(json, ApplicationRequest.class);
            validate(request);
            List<Integer> ids = new ArrayList<>();
            ids.add(save(user, request));
            return JsonService.ok(new IdsResponse(ids));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse addApplications(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.PARTICIPANT);
            competitionService.requireOpen();
            ApplicationsRequest request = JsonService.read(json, ApplicationsRequest.class);
            if (request.getApplications() == null || request.getApplications().isEmpty()) {
                throw new CompetitionException("Список заявок пуст");
            }
            for (ApplicationRequest application : request.getApplications()) { validate(application); }
            List<Integer> ids = new ArrayList<>();
            for (ApplicationRequest application : request.getApplications()) { ids.add(save(user, application)); }
            return JsonService.ok(new IdsResponse(ids));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    private static void validate(ApplicationRequest request) {
        if (request == null) { throw new CompetitionException("Заявка не заполнена"); }
        Validation.text(request.getTitle(), "title");
        Validation.text(request.getDescription(), "description");
        Validation.directions(request.getDirections());
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new CompetitionException("Сумма должна быть положительным целым числом");
        }
    }
    private int save(User user, ApplicationRequest request) {
        int id = applicationDao.nextApplicationId();
        applicationDao.saveApplication(new Application(id, user.getId(), request.getTitle().trim(),
                request.getDescription().trim(), Validation.directions(request.getDirections()), request.getAmount()));
        return id;
    }
    private Application find(Integer id) {
        Application application = applicationDao.getApplication(Validation.id(id));
        if (application == null) { throw new CompetitionException("Заявка не найдена"); }
        return application;
    }
    public ServerResponse cancelApplication(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.PARTICIPANT);
            competitionService.requireOpen();
            IdRequest request = JsonService.read(json, IdRequest.class);
            Application application = find(request.getApplicationId());
            if (application.getParticipantId() != user.getId()) { throw new CompetitionException("Это чужая заявка"); }
            applicationDao.removeApplication(application.getId());
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse getMyApplications(String token) {
        try {
            User user = userService.requireRole(token, Role.PARTICIPANT);
            List<ApplicationResponse> result = new ArrayList<>();
            for (Application application : applicationDao.getApplications()) {
                if (application.getParticipantId() == user.getId()) { result.add(toResponse(application)); }
            }
            return JsonService.ok(new ApplicationsResponse(result));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse getApplications(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.EXPERT);
            DirectionsRequest request = JsonService.read(json, DirectionsRequest.class);
            List<String> directions = Validation.directions(request.getDirections());
            if (!user.getDirections().containsAll(directions)) {
                throw new CompetitionException("Можно искать только по своим направлениям");
            }
            List<ApplicationResponse> result = new ArrayList<>();
            for (Application application : applicationDao.getApplications()) {
                if (Validation.intersects(directions, application.getDirections())) { result.add(toResponse(application)); }
            }
            return JsonService.ok(new ApplicationsResponse(result));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse getRatedApplications(String token) {
        try {
            User user = userService.requireRole(token, Role.EXPERT);
            List<ApplicationResponse> result = new ArrayList<>();
            for (Application application : applicationDao.getApplications()) {
                if (applicationDao.getRating(user.getId(), application.getId()) != null) { result.add(toResponse(application)); }
            }
            return JsonService.ok(new ApplicationsResponse(result));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse rateApplication(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.EXPERT);
            competitionService.requireOpen();
            RatingRequest request = JsonService.read(json, RatingRequest.class);
            Application application = find(request.getApplicationId());
            if (request.getValue() == null || request.getValue() < 1 || request.getValue() > 5) {
                throw new CompetitionException("Оценка должна быть от 1 до 5");
            }
            if (!Validation.intersects(user.getDirections(), application.getDirections())) {
                throw new CompetitionException("Направление заявки не соответствует специализации");
            }
            applicationDao.saveRating(new Rating(user.getId(), application.getId(), request.getValue()));
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    public ServerResponse deleteRating(String token, String json) {
        try {
            User user = userService.requireRole(token, Role.EXPERT);
            competitionService.requireOpen();
            IdRequest request = JsonService.read(json, IdRequest.class);
            Application application = find(request.getApplicationId());
            if (applicationDao.getRating(user.getId(), application.getId()) == null) {
                throw new CompetitionException("У вас нет оценки этой заявки");
            }
            applicationDao.removeRating(user.getId(), application.getId());
            return JsonService.empty();
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
    private ApplicationResponse toResponse(Application application) {
        return new ApplicationResponse(application.getId(), application.getParticipantId(), application.getTitle(),
                application.getDescription(), application.getDirections(), application.getAmount(),
                competitionService.average(application.getId()), applicationDao.getRatings(application.getId()).size());
    }
}
