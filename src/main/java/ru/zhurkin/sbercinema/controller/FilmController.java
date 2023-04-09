package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.dto.DirectorDTO;
import ru.zhurkin.sbercinema.dto.FilmDTO;
import ru.zhurkin.sbercinema.dto.request.AddFilmDirectorDTO;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.service.FilmService;
import ru.zhurkin.sbercinema.support.mapper.impl.DirectorMapper;
import ru.zhurkin.sbercinema.support.mapper.impl.FilmMapper;

import java.util.HashSet;
import java.util.Set;

@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController extends GenericController<Film, FilmDTO> {

    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final DirectorMapper directorMapper;

    public FilmController(FilmService filmService,
                          FilmMapper filmMapper,
                          DirectorMapper directorMapper) {
        super(filmService, filmMapper);
        this.filmService = filmService;
        this.filmMapper = filmMapper;
        this.directorMapper = directorMapper;
    }

    @PostMapping("/addDirector")
    @Operation(description = "Добавить режисера к фильму", method = "addDirector")
    public ResponseEntity<FilmDTO> addDirector(@RequestBody AddFilmDirectorDTO dto) {

        Film film = filmService.addDirector(dto.getFilmId(), dto.getDirectorId());
        return ResponseEntity.ok(filmMapper.toDto(film));
    }

    @GetMapping("/directors/{id}")
    @Operation(description = "Получить режисеров фильма", method = "getDirectors")
    public ResponseEntity<Set<DirectorDTO>> getDirectors(@PathVariable Long id) {

        Set<Director> directors = filmService.getDirectors(id);
        return ResponseEntity.ok(new HashSet<>(directorMapper.toDtos(directors)));
    }

}
