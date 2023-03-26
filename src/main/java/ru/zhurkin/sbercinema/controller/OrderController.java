package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.repository.OrderRepository;
import ru.zhurkin.sbercinema.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;

@Tag(name = "Заказ фильмов",
        description = "Контроллер для работы по заказу фильмов пользователями")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends GenericController<Order> {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    @Operation(description = "Получить все заказы фильмов пользователя", method = "getUsersOrdersById")
    public ResponseEntity<List<Order>> getUsersOrdersById(@PathVariable Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(CONFLICT).build();
        }

        return ResponseEntity.ok(orderRepository.findByOwner(userOptional.get()));
    }
}
