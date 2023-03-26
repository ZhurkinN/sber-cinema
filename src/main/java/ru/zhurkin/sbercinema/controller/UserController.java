package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.sbercinema.dto.request.ChangeUserRoleDTO;
import ru.zhurkin.sbercinema.model.Role;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.repository.RoleRepository;
import ru.zhurkin.sbercinema.repository.UserRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;

@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями фильмотеки")
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends GenericController<User> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository) {
        super(userRepository);
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/role")
    @Operation(description = "Изменить роль пользователя", method = "setRole")
    public ResponseEntity<User> setRole(@RequestBody ChangeUserRoleDTO dto) {

        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Role> role = roleRepository.findById(dto.getRoleId());
        if (userOptional.isEmpty() || role.isEmpty()) {
            return ResponseEntity.status(CONFLICT).build();
        }

        User user = userOptional.get();
        user.setRole(role.get());

        return ResponseEntity.ok(userRepository.save(user));
    }
}
