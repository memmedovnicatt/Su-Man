package com.nicat.suman.service;

import com.nicat.suman.dao.entity.User;
import com.nicat.suman.dao.repository.UserRepository;
import com.nicat.suman.mapper.UserMapper;
import com.nicat.suman.model.dto.request.LoginRequest;
import com.nicat.suman.model.dto.request.RegisterRequest;
import com.nicat.suman.model.dto.response.LoginResponse;
import com.nicat.suman.model.dto.response.RegisterResponse;
import com.nicat.suman.model.dto.response.UserResponse;
import com.nicat.suman.model.enums.Role;
import com.nicat.suman.model.exception.AlreadyExistsException;
import com.nicat.suman.model.exception.NotFoundException;
import com.nicat.suman.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    BCryptPasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserMapper userMapper;
    SecurityUtil securityUtil;

    public RegisterResponse register(@Valid RegisterRequest registerRequest) {
        boolean existPhone = userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber());
        boolean existUsername = userRepository.existsByUsername(registerRequest.getUsername());
        if (existPhone){
            log.info("phoneNumber:{} is already exist",registerRequest.getPhoneNumber());
            throw new AlreadyExistsException("phoneNumber:"+registerRequest.getPhoneNumber()+" is already exist");
        }

        if (existUsername){
            log.info("username:{} is already exist",registerRequest.getUsername());
            throw new AlreadyExistsException("username:"+registerRequest.getUsername()+" is already exist");
        }
        log.info("Starting user registration process");
        User user = User.builder()
                .name(registerRequest.getName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(Role.COURIER)
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        log.info("User registered successfully with username: {}", user.getUsername());
        return userMapper.toRegisterResponseDto(user);
    }

    public LoginResponse login(@Valid LoginRequest loginRequest) {
        log.info("Starting login process for username: {}", loginRequest.getUsername());
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("This user not found with this username: " + loginRequest.getUsername()));
        log.info("User found in database, proceeding to authenticate");
        securityUtil.authenticate(loginRequest);
        log.info("User authenticated successfully");
        return userMapper.toLoginResponse(user);
    }

    public UserResponse getById(Long id) {
        log.info("getById method was started for UserService");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user id:" + id + " was not found"));
        log.info("user was found with id:{}", id);
        return userMapper.toUserResponse(user);
    }
}