package com.example.harness.order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String customerId;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal amount;
    @Column(nullable = false) private String status;
    @Column(nullable = false) private Instant createdAt;
    protected OrderEntity() { }
    public OrderEntity(String customerId, BigDecimal amount) {
        this.customerId = customerId; this.amount = amount; this.status = "CREATED"; this.createdAt = Instant.now();
    }
    public Long getId() { return id; }
    public String getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
}
