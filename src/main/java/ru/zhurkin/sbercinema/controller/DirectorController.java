package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.dto.request.AddFilmDirectorDTO;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.repository.DirectorRepository;
import ru.zhurkin.sbercinema.repository.FilmRepository;

import java.util.Optional;
import java.util.Set;

@Tag(name = "Фильмы",
        description = "Контроллер для работы с режисерами")
@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController extends GenericController<Director> {

    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public DirectorController(DirectorRepository directorRepository,
                              FilmRepository filmRepository) {
        super(directorRepository);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }

    @PostMapping("/addFilm")
    @Operation(description = "Добавить фильм к режисеру", method = "addFilm")
    public ResponseEntity<Director> addFilm(@RequestBody AddFilmDirectorDTO dto) {

        Optional<Director> directorOptional = directorRepository.findById(dto.getDirectorId());
        Optional<Film> filmOptional = filmRepository.findById(dto.getFilmId());

        if (directorOptional.isEmpty() || filmOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Director director = directorOptional.get();
        director.getFilms().add(filmOptional.get());

        return ResponseEntity.ok(directorRepository.save(director));
    }

    @GetMapping("/films/{id}")
    @Operation(description = "Получить фильмы режисера", method = "getFilms")
    public ResponseEntity<Set<Film>> getFilms(@PathVariable Long id) {

        Optional<Director> directorOptional = directorRepository.findById(id);
        if (directorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(directorOptional.get().getFilms());
    }

}
