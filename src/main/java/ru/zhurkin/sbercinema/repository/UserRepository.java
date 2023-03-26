package ru.zhurkin.sbercinema.repository;

import org.springframework.stereotype.Repository;
import ru.zhurkin.sbercinema.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {

}
