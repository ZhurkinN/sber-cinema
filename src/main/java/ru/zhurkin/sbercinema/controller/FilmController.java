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
        description = "Контроллер для работы с фильмами")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController extends GenericController<Film> {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmController(FilmRepository filmRepository,
                          DirectorRepository directorRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    @PostMapping("/addDirector")
    @Operation(description = "Добавить режисера к фильму", method = "addDirector")
    public ResponseEntity<Film> addDirector(@RequestBody AddFilmDirectorDTO dto) {

        Optional<Director> directorOptional = directorRepository.findById(dto.getDirectorId());
        Optional<Film> filmOptional = filmRepository.findById(dto.getFilmId());

        if (directorOptional.isEmpty() || filmOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Film film = filmOptional.get();
        film.getDirectors().add(directorOptional.get());

        return ResponseEntity.ok(filmRepository.save(film));
    }

    @GetMapping("/directors/{id}")
    @Operation(description = "Получить режисеров фильма", method = "getDirectors")
    public ResponseEntity<Set<Director>> getDirectors(@PathVariable Long id) {

        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filmOptional.get().getDirectors());
    }

}
