package com.nicat.suman.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    String customerAddress;
    String customerName;
    String customerSurname;
    String customerPhoneNumber;
    Double totalPrice;
    Long totalCarboyCount;
}