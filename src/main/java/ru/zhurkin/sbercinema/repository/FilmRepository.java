package ru.zhurkin.sbercinema.repository;

import org.springframework.stereotype.Repository;
import ru.zhurkin.sbercinema.model.Film;

@Repository
public interface FilmRepository extends GenericRepository<Film> {

}
