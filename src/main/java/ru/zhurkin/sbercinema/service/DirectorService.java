package ru.zhurkin.sbercinema.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.repository.DirectorRepository;
import ru.zhurkin.sbercinema.repository.FilmRepository;

import java.util.Optional;
import java.util.Set;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.RECORD_NOT_FOUND;

@Service
public class DirectorService extends GenericService<Director> {

    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public DirectorService(DirectorRepository directorRepository,
                           FilmRepository filmRepository) {
        super(directorRepository);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }

    public Director addFilm(Long filmId,
                            Long directorId) {

        Optional<Director> directorOptional = directorRepository.findById(directorId);
        Optional<Film> filmOptional = filmRepository.findById(filmId);

        if (directorOptional.isEmpty() || filmOptional.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }

        Director director = directorOptional.get();
        director.getFilms().add(filmOptional.get());

        return directorRepository.save(director);
    }

    public Set<Film> getFilms(Long id) {

        Optional<Director> directorOptional = directorRepository.findById(id);
        if (directorOptional.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }

        return directorOptional.get().getFilms();
    }
}
