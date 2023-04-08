package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.service.GenericService;

import java.util.List;

@RequiredArgsConstructor
public abstract class GenericController<T extends GenericEntity> {

    private final GenericService<T> genericService;

    @GetMapping
    @Operation(description = "Получить список всех записей", method = "getAll")
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = genericService.listAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получить запись по ID", method = "getById")
    public ResponseEntity<T> getById(@PathVariable Long id) {

        T entity = genericService.getById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    @Operation(description = "Сохранить новую запись", method = "save")
    public ResponseEntity<T> save(@RequestBody T entity) {

        return ResponseEntity.ok(genericService.create(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить запись по id", method = "delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String answer = genericService.delete(id);
        return ResponseEntity.ok(answer);
    }

    @PutMapping
    @Operation(description = "Изменить запись по id", method = "update")
    public ResponseEntity<T> update(@RequestBody T entity,
                                    @RequestParam Long id) {

        return ResponseEntity.ok(genericService.update(entity, id));
    }
}
