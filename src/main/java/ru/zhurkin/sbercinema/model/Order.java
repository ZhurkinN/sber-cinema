package ru.zhurkin.sbercinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends GenericEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false)
    private User owner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id",
            nullable = false)
    private Film film;

    @Column(name = "rent_date")
    private Date rentDate;

    @Column(name = "rent_period")
    private Long rentPeriod;

    @Column(name = "is_purchased")
    private Boolean isPurchased;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(owner, order.owner)
                && Objects.equals(film, order.film)
                && Objects.equals(rentDate, order.rentDate)
                && Objects.equals(rentPeriod, order.rentPeriod)
                && Objects.equals(isPurchased, order.isPurchased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, film, rentDate, rentPeriod, isPurchased);
    }
}
