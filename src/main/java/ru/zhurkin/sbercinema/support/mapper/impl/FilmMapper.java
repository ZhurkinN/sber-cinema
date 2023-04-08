package ru.zhurkin.sbercinema.support.mapper.impl;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.zhurkin.sbercinema.dto.FilmDTO;
import ru.zhurkin.sbercinema.model.Film;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.repository.DirectorRepository;
import ru.zhurkin.sbercinema.repository.OrderRepository;
import ru.zhurkin.sbercinema.support.mapper.GenericMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    private final DirectorRepository directorRepository;
    private final OrderRepository orderRepository;

    public FilmMapper(ModelMapper modelMapper,
                      DirectorRepository directorRepository,
                      OrderRepository orderRepository) {
        super(modelMapper, Film.class, FilmDTO.class);
        this.directorRepository = directorRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                .addMappings(m -> m.skip(FilmDTO::setOrderIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmDTO::setDirectorIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Film::setOrders)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {

        if (!Objects.isNull(source.getOrderIds())) {
            destination.setOrders(new HashSet<>(orderRepository.findAllById(source.getOrderIds())));
        } else {
            destination.setOrders(Collections.emptySet());
        }

        if (!Objects.isNull(source.getDirectorIds())) {
            destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorIds())));
        } else {
            destination.setDirectors(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {

        destination.setOrderIds(getOrderIds(source));
        destination.setDirectorIds(getDirectorIds(source));
    }

    private Set<Long> getOrderIds(Film entity) {

        if (Objects.isNull(entity) || Objects.isNull(entity.getOrders())) {
            return null;
        }
        return entity.getOrders()
                .stream()
                .map(GenericEntity::getId)
                .collect(Collectors.toSet());
    }

    private Set<Long> getDirectorIds(Film entity) {

        if (Objects.isNull(entity) || Objects.isNull(entity.getOrders())) {
            return null;
        }
        return entity.getDirectors()
                .stream()
                .map(GenericEntity::getId)
                .collect(Collectors.toSet());
    }
}
