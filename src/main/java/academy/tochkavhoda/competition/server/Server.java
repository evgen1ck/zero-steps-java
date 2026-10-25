package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.dto.response.ServerResponse;
import academy.tochkavhoda.competition.service.ApplicationService;
import academy.tochkavhoda.competition.service.CompetitionService;
import academy.tochkavhoda.competition.service.UserService;
import java.time.Clock;
import java.time.Instant;

public class Server {
    private final UserService userService;
    private final ApplicationService applicationService;
    private final CompetitionService competitionService;

    public Server(long fund, double threshold, Instant deadline) {
        this(fund, threshold, deadline, Clock.systemUTC());
    }
    public Server(long fund, double threshold, Instant deadline, Clock clock) {
        competitionService = new CompetitionService(fund, threshold, deadline, clock);
        userService = new UserService(competitionService);
        applicationService = new ApplicationService(userService, competitionService);
    }

    public ServerResponse registerParticipant(String json) {
        return userService.registerParticipant(json);
    }

    public ServerResponse registerExpert(String json) {
        return userService.registerExpert(json);
    }

    public ServerResponse login(String json) {
        return userService.login(json);
    }

    public ServerResponse logout(String token) {
        return userService.logout(token);
    }

    public ServerResponse deleteAccount(String token) {
        return userService.deleteAccount(token);
    }

    public ServerResponse addApplication(String token, String json) {
        return applicationService.addApplication(token, json);
    }

    public ServerResponse addApplications(String token, String json) {
        return applicationService.addApplications(token, json);
    }

    public ServerResponse cancelApplication(String token, String json) {
        return applicationService.cancelApplication(token, json);
    }

    public ServerResponse getMyApplications(String token) {
        return applicationService.getMyApplications(token);
    }

    public ServerResponse getApplications(String token, String json) {
        return applicationService.getApplications(token, json);
    }

    public ServerResponse getRatedApplications(String token) {
        return applicationService.getRatedApplications(token);
    }

    public ServerResponse rateApplication(String token, String json) {
        return applicationService.rateApplication(token, json);
    }

    public ServerResponse deleteRating(String token, String json) {
        return applicationService.deleteRating(token, json);
    }

    public ServerResponse getResults(String token) {
        return competitionService.getResults(token);
    }
}
