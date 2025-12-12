package com.mon.marketplace.orderservice;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDto {
    private UUID id;
    private String userId;
    private BigDecimal total;
    private String status;

    public OrderDto() {}
    public OrderDto(UUID id, String userId, BigDecimal total, String status) {
        this.id = id; this.userId = userId; this.total = total; this.status = status;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}