package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.dto.UserDTO;
import ru.zhurkin.sbercinema.dto.request.ChangeUserRoleDTO;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.service.UserService;
import ru.zhurkin.sbercinema.support.mapper.impl.UserMapper;

@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями фильмотеки")
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends GenericController<User, UserDTO> {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        super(userService, userMapper);
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/role")
    @Operation(description = "Изменить роль пользователя", method = "setRole")
    public ResponseEntity<UserDTO> setRole(@RequestBody ChangeUserRoleDTO dto) {

        try {
            User savedUser = userService.addRole(dto.getUserId(), dto.getRoleId());
            return ResponseEntity.ok(userMapper.toDto(savedUser));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound()
                    .header("message", e.getMessage())
                    .build();
        }
    }
}
