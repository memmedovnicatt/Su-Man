package com.nicat.suman.controller;

import com.nicat.suman.model.dto.request.LoginRequest;
import com.nicat.suman.model.dto.request.RegisterRequest;
import com.nicat.suman.model.dto.response.LoginResponse;
import com.nicat.suman.model.dto.response.RegisterResponse;
import com.nicat.suman.model.dto.response.UserResponse;
import com.nicat.suman.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "User Controller", description = "Handles user operations")
//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user by providing required information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "User already exists")// to do : 409 must be impl in service
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        RegisterResponse registerResponse = userService.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }


    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns login response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Get user by ID",
            description = "Fetches a single user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserResponse userResponse = userService.getById(id);
        return ResponseEntity.ok(userResponse);
    }
}