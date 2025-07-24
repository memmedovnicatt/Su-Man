package com.nicat.suman.dao.repository;

import com.nicat.suman.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
