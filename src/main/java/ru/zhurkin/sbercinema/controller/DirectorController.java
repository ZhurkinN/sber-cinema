package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.dto.DirectorDTO;
import ru.zhurkin.sbercinema.dto.request.AddFilmDirectorDTO;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.service.DirectorService;
import ru.zhurkin.sbercinema.support.mapper.impl.DirectorMapper;

import java.util.Set;

@Tag(name = "Фильмы",
        description = "Контроллер для работы с режисерами")
@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController extends GenericController<Director, DirectorDTO> {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    public DirectorController(DirectorService directorService,
                              DirectorMapper directorMapper) {
        super(directorService, directorMapper);
        this.directorService = directorService;
        this.directorMapper = directorMapper;
    }

    @PostMapping("/addFilm")
    @Operation(description = "Добавить фильм к режисеру", method = "addFilm")
    public ResponseEntity<Director> addFilm(@RequestBody AddFilmDirectorDTO dto) {

        Director director = directorService.addFilm(dto.getFilmId(), dto.getDirectorId());
        return ResponseEntity.ok(director);
    }

    @GetMapping("/films/{id}")
    @Operation(description = "Получить фильмы режисера", method = "getFilms")
    public ResponseEntity<Set<Film>> getFilms(@PathVariable Long id) {

        return ResponseEntity.ok(directorService.getFilms(id));
    }

}
