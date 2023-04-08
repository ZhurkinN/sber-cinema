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
public class DirectorDTO extends GenericDTO {

    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    private Set<Long> filmIds;
}
