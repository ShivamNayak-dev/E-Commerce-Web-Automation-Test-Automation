package com.shopflow.controller;

import com.shopflow.model.Order;
import com.shopflow.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orders;

    public OrderController(OrderRepository orders) {
        this.orders = orders;
    }

    @PostMapping
    public Order create(@RequestBody Map<String, Object> body) {
        Order order = new Order();
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setEmail((String) body.get("email"));
        order.setTotalAmount(((Number) body.get("totalAmount")).doubleValue());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());
        return orders.save(order);
    }

    @GetMapping
    public List<Order> byEmail(@RequestParam String email) {
        return orders.findByEmail(email);
    }

    @GetMapping("/{orderNumber}")
    public Order one(@PathVariable String orderNumber) {
        return orders.findByOrderNumber(orderNumber).orElseThrow();
    }
}
