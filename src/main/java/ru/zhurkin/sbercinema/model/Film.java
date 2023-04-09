package ru.zhurkin.sbercinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film extends GenericEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "premier_year")
    private Integer premierYear;

    @Column(name = "country")
    private String country;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"),
            inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    private Set<Director> directors = new HashSet<>();

    @OneToMany(mappedBy = "film",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return Objects.equals(title, film.title)
                && Objects.equals(premierYear, film.premierYear)
                && Objects.equals(country, film.country)
                && Objects.equals(genre, film.genre)
                && Objects.equals(directors, film.directors)
                && Objects.equals(orders, film.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, premierYear, country, genre, directors, orders);
    }
}
