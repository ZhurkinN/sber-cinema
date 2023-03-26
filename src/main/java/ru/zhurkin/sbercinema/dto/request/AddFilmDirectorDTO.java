package ru.zhurkin.sbercinema.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddFilmDirectorDTO {

    private Long filmId;
    private Long directorId;
}
