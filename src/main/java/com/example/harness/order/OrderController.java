package com.example.harness.order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity create(@Valid @RequestBody CreateOrderRequest request) { return service.create(request); }
    @GetMapping("/{id}")
    public OrderEntity get(@PathVariable long id) {
        return service.find(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
