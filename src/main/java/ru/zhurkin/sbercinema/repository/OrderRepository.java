package ru.zhurkin.sbercinema.repository;

import org.springframework.stereotype.Repository;
import ru.zhurkin.sbercinema.model.Order;

@Repository
public interface OrderRepository extends GenericRepository<Order> {
}
