package com.nicat.suman.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddRequest {
    Long carboyCount;
    Long courierId;
    Long customerId;
}