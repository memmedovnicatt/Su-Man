package com.nicat.suman.model.dto.response;

import com.nicat.suman.model.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderAllResponse {
    String customerFullName;
    String customerPhoneNumber;
    String customerAddress;
    LocalDate orderDate;

    Double price;
    Long carboyCount;
    String courierFullName;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

}