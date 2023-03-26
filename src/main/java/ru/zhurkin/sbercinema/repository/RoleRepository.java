package ru.zhurkin.sbercinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.sbercinema.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
