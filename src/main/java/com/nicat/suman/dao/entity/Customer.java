package com.nicat.suman.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @PrePersist
    protected void onCreate() {
        if (currency == null) {
            currency = "AZN";
        }
    }
}