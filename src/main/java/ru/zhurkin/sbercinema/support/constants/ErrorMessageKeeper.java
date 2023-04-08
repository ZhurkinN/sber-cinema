package ru.zhurkin.sbercinema.support.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageKeeper {

    /*
    GenericController's errors
     */
    public static final String RECORD_NOT_FOUND = "Запись с таким id не была найдена!";
    public static final String RECORD_ALREADY_EXISTS = "Запись уже существует!";

    /*
    Mapper's errors
     */
    public static final String FILM_NOT_FOUND = "Фильм не был найден!";
    public static final String USER_NOT_FOUND = "Пользователь не был найден!";
    public static final String ROLE_NOT_FOUND = "Роль не была найдена!";
}
