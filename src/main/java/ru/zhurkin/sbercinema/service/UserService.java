package ru.zhurkin.sbercinema.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.model.Role;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.repository.RoleRepository;
import ru.zhurkin.sbercinema.repository.UserRepository;

import java.util.Optional;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.RECORD_NOT_FOUND;

@Service
public class UserService extends GenericService<User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    protected UserService(UserRepository userRepository,
                          RoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User addRole(Long userId,
                        Long roleId) {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (userOptional.isEmpty() || role.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }
        User user = userOptional.get();
        user.setRole(role.get());

        return userRepository.save(user);
    }
}
