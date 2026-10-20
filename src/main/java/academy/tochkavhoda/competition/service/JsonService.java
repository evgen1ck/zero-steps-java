package academy.tochkavhoda.competition.service;

import com.google.gson.*;
import academy.tochkavhoda.competition.dto.response.*;
import academy.tochkavhoda.competition.exception.CompetitionException;

public class JsonService {
    private static final Gson GSON = new GsonBuilder().setStrictness(Strictness.STRICT).create();

    public static <T> T read(String json, Class<T> type) {
        try {
            JsonObject object = GSON.fromJson(json, JsonObject.class);
            if (object == null) { throw new CompetitionException("Нужен JSON-объект"); }
            return GSON.fromJson(json, type);
        } catch (JsonParseException e) {
            throw new CompetitionException("Неверный формат JSON или тип поля");
        }
    }
    public static ServerResponse ok(Object response) {
        return new ServerResponse(200, GSON.toJson(response));
    }
    public static ServerResponse empty() { return new ServerResponse(200, "{}"); }
    public static ServerResponse error(CompetitionException e) {
        return new ServerResponse(400, GSON.toJson(new ErrorResponse(e.getMessage())));
    }
}
