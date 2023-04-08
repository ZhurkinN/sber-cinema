package ru.zhurkin.sbercinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericDTO {

    protected Long id;
    protected LocalDateTime createdWhen = LocalDateTime.now();
    protected String createdBy = "Nikita Zhurkin";
}
