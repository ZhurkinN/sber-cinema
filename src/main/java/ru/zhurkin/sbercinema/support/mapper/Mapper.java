package ru.zhurkin.sbercinema.support.mapper;

import ru.zhurkin.sbercinema.dto.GenericDTO;
import ru.zhurkin.sbercinema.model.GenericEntity;

import java.util.Collection;
import java.util.List;

public interface Mapper<E extends GenericEntity, D extends GenericDTO> {

    E toEntity(D dto);

    List<E> toEntities(List<D> dtos);

    D toDto(E entity);

    Collection<D> toDtos(Collection<E> entities);
}
