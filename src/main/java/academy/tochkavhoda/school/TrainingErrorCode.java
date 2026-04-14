package academy.tochkavhoda.school;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Неверное имя стажёра"),
    TRAINEE_WRONG_LASTNAME("Неверная фамилия стажёра"),
    TRAINEE_WRONG_RATING("Неверный рейтинг стажёра"),
    GROUP_WRONG_NAME("Неверное название группы"),
    GROUP_WRONG_ROOM("Неверный номер комнаты"),
    SCHOOL_WRONG_NAME("Неверное название школы"),
    TRAINEE_NOT_FOUND("Стажёр не найден"),
    GROUP_NOT_FOUND("Группа не найдена"),
    DUPLICATE_GROUP_NAME("Группа с таким именем уже есть"),
    DUPLICATE_TRAINEE("Стажёр уже есть"),
    DUPLICATE_PASSPORT("Такой паспорт уже есть"),
    PASSPORT_NOT_FOUND("Паспорт не найден"),
    EMPTY_TRAINEE_QUEUE("Очередь стажёров пуста");

    private final String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
