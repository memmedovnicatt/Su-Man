package com.nicat.suman.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String customerAddress;
    String customerName;
    String customerSurname;
    String customerPhoneNumber;
    Double totalPrice;
    Long totalCarboyCount;
}
