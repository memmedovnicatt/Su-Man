package com.nicat.suman.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddRequest {
    Long carboyCount;
    Long courierId;
    Long customerId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate orderDate;

    String orderTime;

}