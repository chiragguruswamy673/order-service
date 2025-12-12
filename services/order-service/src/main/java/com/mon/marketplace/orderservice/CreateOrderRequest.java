package com.mon.marketplace.orderservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

public class CreateOrderRequest {
    @NotBlank
    private String userId;

    @NotNull
    private Map<String, Integer> items;

    @NotNull
    private BigDecimal total;

    // --- Getters and Setters ---
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}