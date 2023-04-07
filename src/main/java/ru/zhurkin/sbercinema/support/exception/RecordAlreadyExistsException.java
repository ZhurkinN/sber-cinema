package ru.zhurkin.sbercinema.support.exception;

public class RecordAlreadyExistsException extends RuntimeException {

    public RecordAlreadyExistsException() {
    }

    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}
