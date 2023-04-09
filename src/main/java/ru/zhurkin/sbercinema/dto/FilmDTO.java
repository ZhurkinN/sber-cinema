package ru.zhurkin.sbercinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO extends GenericDTO {

    private String title;
    private Integer premierYear;
    private String country;
    private String genre;
    private Set<Long> directors;
    private Set<Long> orders;
}
