package ru.zhurkin.sbercinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id",
            unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false)
    private User owner;
    @ManyToOne
    @JoinColumn(name = "film_id",
            nullable = false)
    private Film film;

    @Column(name = "rent_date")
    private Date rentDate;

    @Column(name = "rent_period")
    private Long rentPeriod;

    @Column(name = "is_purchased")
    private Boolean isPurchased;


}
