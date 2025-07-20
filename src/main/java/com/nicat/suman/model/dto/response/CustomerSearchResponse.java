package com.nicat.suman.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerSearchResponse {
    String name;
    String surname;
    Double price;
    String phoneNumber;
    String currency;
    String address;
}