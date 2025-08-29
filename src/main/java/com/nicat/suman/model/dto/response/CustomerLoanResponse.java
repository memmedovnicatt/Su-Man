package com.nicat.suman.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerLoanResponse {
    String name;
    String surname;
    String address;
    String phoneNumber;
    Long loanCarboyCount;
}