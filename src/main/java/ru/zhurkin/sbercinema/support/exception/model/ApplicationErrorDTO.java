package ru.zhurkin.sbercinema.support.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationErrorDTO {

    String message;
}
