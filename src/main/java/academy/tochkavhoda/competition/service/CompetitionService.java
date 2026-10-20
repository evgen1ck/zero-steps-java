package academy.tochkavhoda.competition.service;

import java.util.*;
import academy.tochkavhoda.competition.dao.*;
import academy.tochkavhoda.competition.daoimpl.*;
import academy.tochkavhoda.competition.model.*;
import academy.tochkavhoda.competition.dto.request.*;
import academy.tochkavhoda.competition.dto.response.*;
import academy.tochkavhoda.competition.exception.CompetitionException;
import java.time.Clock;
import java.time.Instant;

public class CompetitionService {
    private final CompetitionDao competitionDao = new CompetitionDaoImpl();
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final long fund;
    private final double threshold;
    private final Instant deadline;
    private final Clock clock;

    public CompetitionService(long fund, double threshold, Instant deadline, Clock clock) {
        if (fund < 0 || !Double.isFinite(threshold) || threshold < 0 || threshold > 5
                || deadline == null || clock == null) {
            throw new IllegalArgumentException("Неверные настройки конкурса");
        }
        competitionDao.initialize();
        this.fund = fund; this.threshold = threshold; this.deadline = deadline; this.clock = clock;
    }
    public void requireOpen() {
        finishIfNeeded();
        if (competitionDao.getResult() != null) { throw new CompetitionException("Конкурс завершён"); }
    }
    public double average(int applicationId) {
        List<Rating> ratings = applicationDao.getRatings(applicationId);
        if (ratings.isEmpty()) { return 0; }
        long sum = 0;
        for (Rating rating : ratings) { sum += rating.getValue(); }
        return (double) sum / ratings.size();
    }
    public void finishIfNeeded() {
        if (competitionDao.getResult() != null || clock.instant().isBefore(deadline)) { return; }
        List<ResultItem> ranking = new ArrayList<>();
        for (Application application : applicationDao.getApplications()) {
            ranking.add(new ResultItem(application.getId(), average(application.getId()), application.getAmount(), false));
        }
        Collections.shuffle(ranking);
        Collections.sort(ranking, new Comparator<ResultItem>() {
            @Override
            public int compare(ResultItem first, ResultItem second) {
                int comparison = Double.compare(second.getAverage(), first.getAverage());
                if (comparison != 0) { return comparison; }
                return Long.compare(first.getAmount(), second.getAmount());
            }
        });
        long remaining = fund;
        List<ResultItem> result = new ArrayList<>();
        for (ResultItem item : ranking) {
            boolean funded = item.getAverage() > threshold && item.getAmount() <= remaining;
            if (funded) { remaining -= item.getAmount(); }
            result.add(new ResultItem(item.getApplicationId(), item.getAverage(), item.getAmount(), funded));
        }
        competitionDao.saveResult(new CompetitionResult(result, remaining));
    }
    public ServerResponse getResults(String token) {
        try {
            if (userDao.getUserByToken(token) == null) { throw new CompetitionException("Недействительный токен"); }
            finishIfNeeded();
            CompetitionResult result = competitionDao.getResult();
            if (result == null) { throw new CompetitionException("Итоги ещё не подведены"); }
            List<ResultItemResponse> ranking = new ArrayList<>();
            for (ResultItem item : result.getRanking()) {
                ranking.add(new ResultItemResponse(item.getApplicationId(), item.getAverage(), item.getAmount(), item.getFunded()));
            }
            return JsonService.ok(new ResultsResponse(ranking, result.getRemainingFund()));
        } catch (CompetitionException e) { return JsonService.error(e); }
    }
}
