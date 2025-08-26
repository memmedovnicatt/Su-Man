package com.nicat.suman.dao.entity;

import com.nicat.suman.model.enums.OrderStatus;
import com.nicat.suman.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //customer_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    //courier_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    User user;

    Long carboyCount;
    Double price;


    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @PrePersist
    protected void onCreate() {
        if (orderStatus == null) {
            orderStatus = OrderStatus.PENDING;
        }
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate orderDate;

    String orderTime;

    LocalDateTime deleteAt;
    LocalDateTime updateAt;
}