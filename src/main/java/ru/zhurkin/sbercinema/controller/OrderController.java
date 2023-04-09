package ru.zhurkin.sbercinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.sbercinema.dto.OrderDTO;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.service.OrderService;
import ru.zhurkin.sbercinema.support.mapper.impl.OrderMapper;

import java.util.List;

@Tag(name = "Заказ фильмов",
        description = "Контроллер для работы по заказу фильмов пользователями")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends GenericController<Order, OrderDTO> {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper) {
        super(orderService, orderMapper);
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "Получить все заказы фильмов пользователя", method = "getUsersOrdersById")
    public ResponseEntity<List<OrderDTO>> getUsersOrdersById(@PathVariable Long userId) {

        List<Order> orders = orderService.getUsersOrders(userId);
        return ResponseEntity.ok((List<OrderDTO>) orderMapper.toDtos(orders));
    }
}
