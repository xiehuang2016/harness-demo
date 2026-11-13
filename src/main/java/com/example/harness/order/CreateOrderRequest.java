package com.example.harness.order;
import javax.validation.constraints.*;
import java.math.BigDecimal;
public class CreateOrderRequest {
    @NotBlank private String customerId;
    @NotNull @DecimalMin("0.01") @Digits(integer = 10, fraction = 2) private BigDecimal amount;
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
