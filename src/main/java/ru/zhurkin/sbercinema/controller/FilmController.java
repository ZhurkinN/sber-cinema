package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.repository.FilmRepository;

@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController extends GenericController<Film> {

    public FilmController(FilmRepository filmRepository) {
        super(filmRepository);
    }

}
