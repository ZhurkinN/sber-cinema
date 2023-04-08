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
public class FilmService extends GenericService<Film> {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;


    public FilmService(FilmRepository filmRepository,
                       DirectorRepository directorRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public Film addDirector(Long filmId,
                            Long directorId) {

        Optional<Director> directorOptional = directorRepository.findById(directorId);
        Optional<Film> filmOptional = filmRepository.findById(filmId);

        if (directorOptional.isEmpty() || filmOptional.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }
        Film film = filmOptional.get();
        film.getDirectors().add(directorOptional.get());

        return filmRepository.save(film);
    }

    public Set<Director> getDirectors(Long id) {

        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }

        return filmOptional.get().getDirectors();
    }
}
