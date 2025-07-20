package com.nicat.suman.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
    String name;
    String surname;
    String address;
    String phoneNumber;
    Double price;
}