package ru.zhurkin.sbercinema.support.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.zhurkin.sbercinema.dto.GenericDTO;
import ru.zhurkin.sbercinema.model.GenericEntity;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public abstract class GenericMapper<E extends GenericEntity, D extends GenericDTO> implements Mapper<E, D> {

    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Override
    public E toEntity(D dto) {

        if (Objects.isNull(dto)) {
            return null;
        }
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<E> toEntities(List<D> dtos) {

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public D toDto(E entity) {

        if (Objects.isNull(entity)) {
            return null;
        }
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<D> toDtos(List<E> entities) {

        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    protected Converter<D, E> toEntityConverter() {

        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<E, D> toDtoConverter() {

        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificFields(D source, E destination);

    protected abstract void mapSpecificFields(E source, D destination);

}
