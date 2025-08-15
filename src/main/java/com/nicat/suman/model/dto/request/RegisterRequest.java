package com.nicat.suman.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

//    @NotEmpty(message = "Name cannot be null or empty")
//    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long")
//    @Column(columnDefinition = "VARCHAR(255)")
    String name;

//    @NotEmpty(message = "Surname cannot be null or empty")
//    @Size(min = 4, max = 50, message = "Surname must be at most 50 characters long")
//    @Column(columnDefinition = "VARCHAR(255)")
    String surname;

//    @NotEmpty(message = "Username must not be empty")
//    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
//    @Column(columnDefinition = "VARCHAR(255)")
    String username;

//    @Size(min = 5, max = 30, message = "Password must be between 3 adn 30 characters")
//    @NotEmpty(message = "Password cannot be empty")
    String password;

//    @NotEmpty(message = "Phone must not be empty")
//    @Pattern(regexp = "^\\+994(50|51|55|70|77|99)\\d{7}$", message = "Phone number must be valid '+9940000000000'.Example: +994516125092")
//    @Column(columnDefinition = "VARCHAR(20)")
//    @Size(min = 10, max = 20, message = "Phone must be between 10 and 20 characters")
    String phoneNumber;
}