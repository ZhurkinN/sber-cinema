package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.service.OrderService;

import java.util.List;

@Tag(name = "Заказ фильмов",
        description = "Контроллер для работы по заказу фильмов пользователями")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends GenericController<Order> {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    @Operation(description = "Получить все заказы фильмов пользователя", method = "getUsersOrdersById")
    public ResponseEntity<List<Order>> getUsersOrdersById(@PathVariable Long userId) {

        return ResponseEntity.ok(orderService.getUsersOrders(userId));
    }
}
