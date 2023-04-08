package ru.zhurkin.sbercinema.service;

import lombok.RequiredArgsConstructor;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.repository.GenericRepository;
import ru.zhurkin.sbercinema.support.exception.RecordAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.RECORD_ALREADY_EXISTS;
import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.RECORD_NOT_FOUND;
import static ru.zhurkin.sbercinema.support.constants.ResponseConstantsKeeper.RECORD_DELETED;

@RequiredArgsConstructor
public abstract class GenericService<T extends GenericEntity> {

    private final GenericRepository<T> repository;

    public List<T> listAll() {
        return repository.findAll();
    }

    public T getById(Long id) {
        Optional<T> optionalRecord = repository.findById(id);

        if (optionalRecord.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }

        return optionalRecord.get();
    }

    public T create(T entity) {

        if (entity.getId() != null && repository.existsById(entity.getId())) {
            throw new RecordAlreadyExistsException(RECORD_ALREADY_EXISTS);
        }
        entity.setCreatedBy("Nikita Zhurkin");
        entity.setCreatedWhen(LocalDateTime.now());
        return repository.save(entity);
    }

    public T update(T entity, Long id) {
        entity.setId(id);
        return repository.save(entity);
    }

    public String delete(Long id) {
        Optional<T> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }
        repository.delete(entity.get());
        return RECORD_DELETED;
    }

}
