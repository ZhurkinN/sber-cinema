package ru.zhurkin.sbercinema.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.repository.OrderRepository;
import ru.zhurkin.sbercinema.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.RECORD_NOT_FOUND;

@Service
public class OrderService extends GenericService<Order> {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getUsersOrders(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(RECORD_NOT_FOUND);
        }

        return orderRepository.findByOwner(userOptional.get());
    }
}
