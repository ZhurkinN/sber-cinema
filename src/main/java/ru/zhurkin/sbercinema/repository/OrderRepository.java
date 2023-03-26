package ru.zhurkin.sbercinema.repository;

import org.springframework.stereotype.Repository;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends GenericRepository<Order> {

    List<Order> findByOwner(User user);
}
