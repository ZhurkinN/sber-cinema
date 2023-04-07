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
}
