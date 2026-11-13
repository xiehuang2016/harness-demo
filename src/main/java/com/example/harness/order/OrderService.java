package com.example.harness.order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class OrderService {
    private final OrderRepository repository;
    public OrderService(OrderRepository repository) { this.repository = repository; }
    @Transactional public OrderEntity create(CreateOrderRequest request) { return repository.save(new OrderEntity(request.getCustomerId(), request.getAmount())); }
    @Transactional(readOnly = true) public Optional<OrderEntity> find(long id) { return repository.findById(id); }
}
