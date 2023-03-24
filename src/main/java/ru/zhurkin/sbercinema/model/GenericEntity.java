package ru.zhurkin.sbercinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericEntity {

    @Id
    @Column(name = "id",
            unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created_when")
    protected LocalDateTime createdWhen;

    @Column(name = "created_by")
    protected String createdBy;

}
