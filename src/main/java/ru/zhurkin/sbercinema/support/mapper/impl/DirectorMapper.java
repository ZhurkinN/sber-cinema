package ru.zhurkin.sbercinema.support.mapper.impl;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.zhurkin.sbercinema.dto.DirectorDTO;
import ru.zhurkin.sbercinema.model.Director;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.repository.FilmRepository;
import ru.zhurkin.sbercinema.support.mapper.GenericMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Director, DirectorDTO> {

    private final FilmRepository filmRepository;

    public DirectorMapper(ModelMapper modelMapper,
                          FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorDTO.class)
                .addMappings(m -> m.skip(DirectorDTO::setFilmIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(DirectorDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorDTO source, Director destination) {

        if (!Objects.isNull(source.getFilmIds())) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmIds())));
        } else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorDTO destination) {

        destination.setFilmIds(getFilmIds(source));
    }

    private Set<Long> getFilmIds(Director entity) {

        if (Objects.isNull(entity) || Objects.isNull(entity.getFilms())) {
            return null;
        }
        return entity.getFilms()
                .stream()
                .map(GenericEntity::getId)
                .collect(Collectors.toSet());
    }
}
