package com.nicat.suman.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String address;
    String name;
    String phoneNumber;
    String surname;
    String currency;
    Double price;
}