package com.nicat.suman.model.dto.response;

import com.nicat.suman.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String name;
    String surname;
    String phoneNumber;
    String username;

    @Enumerated(EnumType.STRING)
    Role role;
}