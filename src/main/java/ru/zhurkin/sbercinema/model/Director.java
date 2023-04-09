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
@Table(name = "directors")
public class Director extends GenericEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "position")
    private String position;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "director_id"),
            foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "film_id"),
            inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"))
    private Set<Film> films = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director director)) return false;
        return Objects.equals(firstName, director.firstName)
                && Objects.equals(lastName, director.lastName)
                && Objects.equals(middleName, director.middleName)
                && Objects.equals(position, director.position)
                && Objects.equals(films, director.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, position, films);
    }
}
