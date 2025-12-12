package com.mon.marketplace.orderservice;

import com.mon.marketplace.orderservice.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {}