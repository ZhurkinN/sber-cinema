package ru.zhurkin.sbercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhurkin.sbercinema.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
