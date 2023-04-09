package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.sbercinema.dto.GenericDTO;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.service.GenericService;
import ru.zhurkin.sbercinema.support.mapper.GenericMapper;

import java.util.List;

@RequiredArgsConstructor
public abstract class GenericController<T extends GenericEntity, N extends GenericDTO> {

    private final GenericService<T> genericService;
    private final GenericMapper<T, N> genericMapper;

    @GetMapping
    @Operation(description = "Получить список всех записей", method = "getAll")
    public ResponseEntity<List<N>> getAll() {
        List<T> entities = genericService.listAll();
        return ResponseEntity.ok((List<N>) genericMapper.toDtos(entities));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получить запись по ID", method = "getById")
    public ResponseEntity<N> getById(@PathVariable Long id) {

        T entity = genericService.getById(id);
        return ResponseEntity.ok(genericMapper.toDto(entity));
    }

    @PostMapping
    @Operation(description = "Сохранить новую запись", method = "save")
    public ResponseEntity<N> save(@RequestBody N dto) {

        T createdEntity = genericService.create(genericMapper.toEntity(dto));
        return ResponseEntity.ok(genericMapper.toDto(createdEntity));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить запись по id", method = "delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String answer = genericService.delete(id);
        return ResponseEntity.ok(answer);
    }

    @PutMapping
    @Operation(description = "Изменить запись по id", method = "update")
    public ResponseEntity<N> update(@RequestBody N dto,
                                    @RequestParam Long id) {

        T updatedEntity = genericService.update(genericMapper.toEntity(dto), id);
        return ResponseEntity.ok(genericMapper.toDto(updatedEntity));
    }
}
