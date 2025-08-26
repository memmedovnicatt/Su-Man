package com.nicat.suman.dao.repository;

import com.nicat.suman.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.customer c " +
            "LEFT JOIN FETCH o.user u")
    List<Order> findAllWithCustomerAndCourier();
}