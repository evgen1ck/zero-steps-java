package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.exception.CompetitionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Validation {
    public static String text(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new CompetitionException("Не заполнено поле: " + field);
        }
        return value.trim();
    }
    public static String login(String value) {
        value = text(value, "login");
        if (!value.matches("[a-zA-Z0-9_]{3,30}")) {
            throw new CompetitionException("Логин: от 3 до 30 латинских букв, цифр или подчёркиваний");
        }
        return value;
    }
    public static String password(String value) {
        text(value, "password");
        if (value.length() < 6 || !value.equals(value.trim())) {
            throw new CompetitionException("Пароль: минимум 6 символов, без пробелов по краям");
        }
        return value;
    }
    public static List<String> directions(List<String> values) {
        if (values == null || values.isEmpty()) { throw new CompetitionException("Укажите направления"); }
        List<String> result = new ArrayList<>();
        for (String value : values) {
            String direction = text(value, "direction").toLowerCase(Locale.ROOT);
            if (!result.contains(direction)) { result.add(direction); }
        }
        return result;
    }
    public static int id(Integer id) {
        if (id == null || id <= 0) { throw new CompetitionException("Некорректный номер заявки"); }
        return id;
    }
    public static boolean intersects(List<String> first, List<String> second) {
        for (String value : first) {
            if (second.contains(value)) { return true; }
        }
        return false;
    }
}
