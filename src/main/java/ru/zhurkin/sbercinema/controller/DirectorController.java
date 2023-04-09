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
import ru.zhurkin.sbercinema.service.DirectorService;
import ru.zhurkin.sbercinema.support.mapper.impl.DirectorMapper;
import ru.zhurkin.sbercinema.support.mapper.impl.FilmMapper;

import java.util.HashSet;
import java.util.Set;

@Tag(name = "Режисеры",
        description = "Контроллер для работы с режисерами")
@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController extends GenericController<Director, DirectorDTO> {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private final FilmMapper filmMapper;

    public DirectorController(DirectorService directorService,
                              DirectorMapper directorMapper,
                              FilmMapper filmMapper) {
        super(directorService, directorMapper);
        this.directorService = directorService;
        this.directorMapper = directorMapper;
        this.filmMapper = filmMapper;
    }

    @PostMapping("/addFilm")
    @Operation(description = "Добавить фильм к режисеру", method = "addFilm")
    public ResponseEntity<DirectorDTO> addFilm(@RequestBody AddFilmDirectorDTO dto) {

        Director director = directorService.addFilm(dto.getFilmId(), dto.getDirectorId());
        return ResponseEntity.ok(directorMapper.toDto(director));
    }

    @GetMapping("/films/{id}")
    @Operation(description = "Получить фильмы режисера", method = "getFilms")
    public ResponseEntity<Set<FilmDTO>> getFilms(@PathVariable Long id) {

        Set<Film> films = directorService.getFilms(id);
        return ResponseEntity.ok(new HashSet<>(filmMapper.toDtos(films)));
    }

}
