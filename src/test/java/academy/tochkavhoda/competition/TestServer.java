package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.dto.request.*;
import academy.tochkavhoda.competition.dto.response.*;
import academy.tochkavhoda.competition.server.Server;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
    private final Gson gson = new Gson();
    private final Instant deadline = Instant.parse("2030-06-01T12:00:00Z");
    private Server server;
    private TestClock clock;
    private String participant;
    private String expert;

    @BeforeEach
    void setUp() {
        Database.getInstance().clear();
        clock = new TestClock(deadline.minusSeconds(60));
        server = new Server(1000, 3, deadline, clock);
        participant = participant("firm1", "Фирма 1");
        expert = expert("expert1", "Иванов", "математика");
    }

    private void ok(ServerResponse response) {
        assertEquals(200, response.getResponseCode(), response.getResponseData());
    }
    private void error(ServerResponse response) {
        assertEquals(400, response.getResponseCode());
        assertFalse(gson.fromJson(response.getResponseData(), ErrorResponse.class).getError().isEmpty());
        assertEquals(1, com.google.gson.JsonParser.parseString(response.getResponseData()).getAsJsonObject().size());
    }
    private String participant(String login, String company) {
        ServerResponse response = server.registerParticipant(gson.toJson(
                new ParticipantRequest("Пётр", "Петров", company, login, "pass123")));
        ok(response);
        assertEquals("{}", response.getResponseData());
        return login(login);
    }
    private String expert(String login, String lastName, String direction) {
        ok(server.registerExpert(gson.toJson(new ExpertRequest("Иван", lastName,
                Arrays.asList(direction), login, "pass123"))));
        return login(login);
    }
    private String login(String login) {
        ServerResponse response = server.login(gson.toJson(new LoginRequest(login, "pass123")));
        ok(response);
        return gson.fromJson(response.getResponseData(), TokenResponse.class).getToken();
    }
    private ApplicationRequest application(long amount) {
        return new ApplicationRequest("Проект", "Описание проекта", Arrays.asList("математика"), amount);
    }
    private int add(long amount) {
        ServerResponse response = server.addApplication(participant, gson.toJson(application(amount)));
        ok(response);
        return gson.fromJson(response.getResponseData(), IdsResponse.class).getApplicationIds().get(0);
    }
    private String id(int id) { return gson.toJson(new IdRequest(id)); }
    private void rate(String token, int id, int value) {
        ok(server.rateApplication(token, gson.toJson(new RatingRequest(id, value))));
    }
    private List<ApplicationResponse> list(ServerResponse response) {
        ok(response);
        return gson.fromJson(response.getResponseData(), ApplicationsResponse.class).getApplications();
    }
    private ResultsResponse results() {
        clock.setInstant(deadline);
        ServerResponse response = server.getResults(participant);
        ok(response);
        return gson.fromJson(response.getResponseData(), ResultsResponse.class);
    }

    @Test
    void testLogoutAndNewToken() {
        ok(server.logout(participant));
        error(server.getMyApplications(participant));
        String newToken = login("firm1");
        assertNotEquals(participant, newToken);
        ok(server.getMyApplications(newToken));
        String newestToken = login("firm1");
        error(server.getMyApplications(newToken));
        ok(server.getMyApplications(newestToken));
    }

    @Test
    void testRegistrationChecks() {
        error(server.registerParticipant(gson.toJson(new ParticipantRequest("И", "П", "Другая", "firm1", "pass123"))));
        error(server.registerParticipant(gson.toJson(new ParticipantRequest("И", "П", " фирма 1 ", "firm2", "pass123"))));
        error(server.registerExpert(gson.toJson(new ExpertRequest("иван", "ИВАНОВ", Arrays.asList("физика"), "other", "pass123"))));
        error(server.registerExpert(gson.toJson(new ExpertRequest("Анна", "Смирнова", Arrays.asList("физика"), "firm1", "pass123"))));
        error(server.registerParticipant(gson.toJson(new ParticipantRequest(" ", "П", "Компания", "other", "pass123"))));
        error(server.registerExpert(gson.toJson(new ExpertRequest("Анна", "Смирнова", Collections.<String>emptyList(), "other", "pass123"))));
        error(server.registerParticipant(gson.toJson(new ParticipantRequest("И", "П", "Компания", "ab", "123"))));
    }

    @Test
    void testInvalidJsonAndMissingFields() {
        for (String json : Arrays.asList(null, "", "null", "[]", "{", "{}", "{login:'abc'}")) {
            error(server.registerParticipant(json));
            error(server.addApplication(participant, json));
            error(server.rateApplication(expert, json));
        }
        error(server.addApplication(participant, "{\"title\":\"X\",\"description\":\"Y\",\"directions\":[\"математика\"],\"amount\":1.5}"));
        int id = add(100);
        error(server.rateApplication(expert, "{\"applicationId\":" + id + ",\"value\":4.5}"));
        error(server.cancelApplication(participant, "{\"applicationId\":1.5}"));
    }

    @Test
    void testWrongPasswordAndRoles() {
        error(server.login(gson.toJson(new LoginRequest("firm1", "wrong123"))));
        error(server.login(gson.toJson(new LoginRequest("unknown", "pass123"))));
        error(server.getMyApplications(null));
        error(server.addApplication(expert, gson.toJson(application(100))));
        int id = add(100);
        error(server.rateApplication(participant, gson.toJson(new RatingRequest(id, 5))));
        error(server.getRatedApplications(participant));
        error(server.getResults("wrong-token"));
    }

    @Test
    void testBatchIsAtomic() {
        ApplicationRequest valid = application(100);
        ApplicationRequest invalid = application(0);
        error(server.addApplications(participant, gson.toJson(new ApplicationsRequest(Arrays.asList(valid, invalid)))));
        assertEquals(0, list(server.getMyApplications(participant)).size());
        error(server.addApplications(participant, gson.toJson(new ApplicationsRequest(Arrays.asList(valid, null)))));
        assertEquals(0, list(server.getMyApplications(participant)).size());
        ok(server.addApplications(participant, gson.toJson(new ApplicationsRequest(Arrays.asList(valid, valid)))));
        assertEquals(2, list(server.getMyApplications(participant)).size());
    }

    @Test
    void testCancelOwnApplication() {
        int id = add(100);
        rate(expert, id, 5);
        String other = participant("firm2", "Фирма 2");
        error(server.cancelApplication(other, id(id)));
        ok(server.cancelApplication(participant, id(id)));
        assertEquals(0, list(server.getMyApplications(participant)).size());
        assertEquals(0, list(server.getRatedApplications(expert)).size());
        error(server.cancelApplication(participant, id(id)));
    }

    @Test
    void testRatingUpdateDeleteAndAverage() {
        int id = add(100);
        String secondExpert = expert("expert2", "Сидоров", "математика");
        rate(expert, id, 5);
        rate(secondExpert, id, 3);
        ApplicationResponse item = list(server.getMyApplications(participant)).get(0);
        assertEquals(4, item.getAverage());
        assertEquals(2, item.getRatingCount());
        rate(expert, id, 1);
        item = list(server.getMyApplications(participant)).get(0);
        assertEquals(2, item.getAverage());
        assertEquals(2, item.getRatingCount());
        ok(server.deleteRating(expert, id(id)));
        assertEquals(3, list(server.getMyApplications(participant)).get(0).getAverage());
        error(server.deleteRating(expert, id(id)));
        ok(server.deleteRating(secondExpert, id(id)));
        assertEquals(0, list(server.getMyApplications(participant)).get(0).getAverage());
    }

    @Test
    void testDirectionsAndRatedList() {
        int first = add(100);
        ApplicationRequest request = new ApplicationRequest("Второй", "Описание", Arrays.asList("физика", "информатика"), 200L);
        ok(server.addApplication(participant, gson.toJson(request)));
        assertEquals(1, list(server.getApplications(expert,
                gson.toJson(new DirectionsRequest(Arrays.asList(" МАТЕМАТИКА "))))).size());
        error(server.getApplications(expert, gson.toJson(new DirectionsRequest(Arrays.asList("математика", "физика")))));
        error(server.rateApplication(expert, gson.toJson(new RatingRequest(first + 1, 5))));
        assertEquals(0, list(server.getRatedApplications(expert)).size());
        rate(expert, first, 4);
        assertEquals(first, list(server.getRatedApplications(expert)).get(0).getId());
        String multi = expert("expert2", "Сидоров", "информатика");
        assertEquals(1, list(server.getApplications(multi,
                gson.toJson(new DirectionsRequest(Arrays.asList("информатика"))))).size());
        rate(multi, first + 1, 5);
    }

    @Test
    void testInvalidRatingAndId() {
        int id = add(100);
        error(server.rateApplication(expert, gson.toJson(new RatingRequest(id, 0))));
        error(server.rateApplication(expert, gson.toJson(new RatingRequest(id, 6))));
        error(server.rateApplication(expert, gson.toJson(new RatingRequest(999, 5))));
        error(server.cancelApplication(participant, id(0)));
    }

    @Test
    void testDeleteParticipantCascadesAndAllowsNewRegistration() {
        int id = add(100);
        rate(expert, id, 5);
        ok(server.deleteAccount(participant));
        error(server.getMyApplications(participant));
        error(server.login(gson.toJson(new LoginRequest("firm1", "pass123"))));
        assertEquals(0, list(server.getRatedApplications(expert)).size());
        participant = participant("firm1", "Фирма 1");
        assertEquals(0, list(server.getMyApplications(participant)).size());
    }

    @Test
    void testDeleteExpertRemovesRatings() {
        int id = add(100);
        rate(expert, id, 5);
        ok(server.deleteAccount(expert));
        error(server.getRatedApplications(expert));
        assertEquals(0, list(server.getMyApplications(participant)).get(0).getRatingCount());
    }

    @Test
    void testRankingBudgetAndThreshold() {
        int expensive = add(2000);
        int medium = add(600);
        int small = add(400);
        int threshold = add(1);
        int unrated = add(1);
        rate(expert, expensive, 5);
        rate(expert, medium, 4);
        rate(expert, small, 4);
        rate(expert, threshold, 3);
        error(server.getResults(participant));
        ResultsResponse result = results();
        List<ResultItemResponse> ranking = result.getRanking();
        assertEquals(expensive, ranking.get(0).getApplicationId());
        assertFalse(ranking.get(0).getFunded());
        assertEquals(small, ranking.get(1).getApplicationId());
        assertTrue(ranking.get(1).getFunded());
        assertEquals(medium, ranking.get(2).getApplicationId());
        assertTrue(ranking.get(2).getFunded());
        assertEquals(threshold, ranking.get(3).getApplicationId());
        assertFalse(ranking.get(3).getFunded());
        assertEquals(unrated, ranking.get(4).getApplicationId());
        assertFalse(ranking.get(4).getFunded());
        assertEquals(0, result.getRemainingFund());
    }

    @Test
    void testBelowThresholdLeavesMoney() {
        int id = add(100);
        rate(expert, id, 3);
        ResultsResponse result = results();
        assertEquals(1000, result.getRemainingFund());
        assertFalse(result.getRanking().get(0).getFunded());
    }

    @Test
    void testAverageNotNumberOfVotes() {
        int first = add(100);
        int second = add(100);
        String secondExpert = expert("expert2", "Сидоров", "математика");
        rate(expert, first, 5);
        rate(expert, second, 4);
        rate(secondExpert, second, 4);
        assertEquals(first, results().getRanking().get(0).getApplicationId());
    }

    @Test
    void testTieResultsDoNotChange() {
        add(1000); add(1000);
        rate(expert, 1, 5); rate(expert, 2, 5);
        ResultsResponse result = results();
        assertTrue(result.getRanking().get(0).getFunded());
        assertFalse(result.getRanking().get(1).getFunded());
        String saved = server.getResults(participant).getResponseData();
        assertEquals(saved, server.getResults(participant).getResponseData());
    }

    @Test
    void testDeadlineBlocksChanges() {
        int id = add(100);
        rate(expert, id, 5);
        clock.setInstant(deadline);
        error(server.addApplication(participant, gson.toJson(application(100))));
        error(server.addApplications(participant, gson.toJson(new ApplicationsRequest(Arrays.asList(application(100))))));
        error(server.cancelApplication(participant, id(id)));
        error(server.rateApplication(expert, gson.toJson(new RatingRequest(id, 1))));
        error(server.deleteRating(expert, id(id)));
        assertEquals(5, results().getRanking().get(0).getAverage());
    }

    @Test
    void testDeletionAfterDeadlineKeepsHistoricalResults() {
        int id = add(100);
        rate(expert, id, 5);
        clock.setInstant(deadline);
        // Удаление само фиксирует итог, даже если getResults ещё не вызывали.
        ok(server.deleteAccount(expert));
        String saved = server.getResults(participant).getResponseData();
        assertEquals(5, results().getRanking().get(0).getAverage());
        String observer = participant("firm2", "Фирма 2");
        ok(server.deleteAccount(participant));
        assertEquals(saved, server.getResults(observer).getResponseData());
    }

    @Test
    void testEmptyCompetition() {
        ResultsResponse result = results();
        assertTrue(result.getRanking().isEmpty());
        assertEquals(1000, result.getRemainingFund());
    }

    private static class TestClock extends Clock {
        private Instant now;
        TestClock(Instant now) { this.now = now; }
        void setInstant(Instant now) { this.now = now; }
        @Override public Instant instant() { return now; }
        @Override public ZoneId getZone() { return ZoneOffset.UTC; }
        @Override public Clock withZone(ZoneId zone) { return Clock.fixed(now, zone); }
    }
}
