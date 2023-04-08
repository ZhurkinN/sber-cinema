package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.dto.request.AddFilmDirectorDTO;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.service.FilmService;

import java.util.Set;

@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController extends GenericController<Film> {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        super(filmService);
        this.filmService = filmService;
    }

    @PostMapping("/addDirector")
    @Operation(description = "Добавить режисера к фильму", method = "addDirector")
    public ResponseEntity<Film> addDirector(@RequestBody AddFilmDirectorDTO dto) {

        Film film = filmService.addDirector(dto.getFilmId(), dto.getDirectorId());
        return ResponseEntity.ok(film);
    }

    @GetMapping("/directors/{id}")
    @Operation(description = "Получить режисеров фильма", method = "getDirectors")
    public ResponseEntity<Set<Director>> getDirectors(@PathVariable Long id) {

        return ResponseEntity.ok(filmService.getDirectors(id));
    }

}
