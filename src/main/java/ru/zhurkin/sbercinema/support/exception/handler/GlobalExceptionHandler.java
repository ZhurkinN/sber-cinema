package ru.zhurkin.sbercinema.support.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.support.exception.RecordAlreadyExistsException;
import ru.zhurkin.sbercinema.support.exception.model.ApplicationErrorDTO;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorDTO> catchRecordAlreadyExistsException(
            RecordAlreadyExistsException e
    ) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ApplicationErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorDTO> catchNotFoundException(
            NotFoundException e
    ) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(NOT_FOUND).body(new ApplicationErrorDTO(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorDTO> catchRuntimeException(
            RuntimeException e
    ) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(NOT_FOUND).body(new ApplicationErrorDTO(e.getMessage()));
    }
}
