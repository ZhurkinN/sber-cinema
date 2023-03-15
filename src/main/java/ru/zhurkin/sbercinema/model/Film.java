package ru.zhurkin.sbercinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film {

    @Id
    @Column(name = "id",
            unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id", nullable = false))
    private Set<Director> directors = new HashSet<>();

    @OneToMany(mappedBy = "film",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Order> orders;

}
