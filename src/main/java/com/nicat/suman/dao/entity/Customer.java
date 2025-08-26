package com.nicat.suman.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;
    String address;
    String phoneNumber;
    Double price; // for carboy
    String currency;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    LocalDateTime deleteAt;
    Integer status;
    Long loanCarboyCount;

    //order relation
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Order> orders;

    @PrePersist
    protected void onCreate() {
        if (currency == null) {
            currency = "AZN";
        }
        if (loanCarboyCount == null) {
            loanCarboyCount = 0L;
        }
    }
}