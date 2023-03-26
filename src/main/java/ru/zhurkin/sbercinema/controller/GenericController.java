package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static ru.zhurkin.sbercinema.constants.ResponseConstantsKeeper.RECORD_DELETED;

@RequiredArgsConstructor
public abstract class GenericController<T extends GenericEntity> {

    private final GenericRepository<T> genericRepository;

    @GetMapping
    @Operation(description = "Получить список всех записей", method = "getAll")
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = genericRepository.findAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получить запись по ID", method = "getById")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        Optional<T> entity = genericRepository.findById(id);

        return entity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(NOT_FOUND).build());
    }

    @PostMapping
    @Operation(description = "Сохранить новую запись", method = "save")
    public ResponseEntity<T> save(@RequestBody T entity) {

        if (entity.getId() != null && genericRepository.existsById(entity.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(CREATED)
                .body(genericRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить запись по id", method = "delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<T> entity = genericRepository.findById(id);
        StringBuilder responseMessage = new StringBuilder();

        if (entity.isPresent()) {
            genericRepository.delete(entity.get());
            responseMessage.append(RECORD_DELETED);
        } else {
            return ResponseEntity.status(CONFLICT).build();
        }

        return ResponseEntity.ok(responseMessage.toString());
    }

    @PutMapping
    @Operation(description = "Изменить запись по id", method = "update")
    public ResponseEntity<T> update(@RequestBody T entity,
                                    @RequestParam Long id) {
        entity.setId(id);
        return ResponseEntity.ok(genericRepository.save(entity));
    }
}
