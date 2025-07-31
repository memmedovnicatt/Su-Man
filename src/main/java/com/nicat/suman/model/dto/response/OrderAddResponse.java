package com.nicat.suman.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddResponse {
    String customerAddress;
    String customerName;
    String customerSurname;
    String customerPhoneNumber;
    Double totalPrice;
    Long totalCarboyCount;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate orderDate;
}