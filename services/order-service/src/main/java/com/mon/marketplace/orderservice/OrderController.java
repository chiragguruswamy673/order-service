package com.mon.marketplace.orderservice;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody CreateOrderRequest req) {
        Order order = new Order();
        order.setUserId(req.getUserId());
        order.setTotal(req.getTotal());
        order.setStatus("pending");

        Order saved = orderRepository.save(order);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable UUID id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable UUID id, @RequestBody String status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}