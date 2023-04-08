package ru.zhurkin.sbercinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends GenericDTO {

    private Long ownerId;
    private Long filmId;
    private Date rentDate;
    private Long rentPeriod;
    private Boolean isPurchased;
}
