package com.nicat.suman.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerListResponse {
    Long id;

    String name;
    String surname;
    String address;
    String phoneNumber;
    Double price; // for carboy
    String currency;
}