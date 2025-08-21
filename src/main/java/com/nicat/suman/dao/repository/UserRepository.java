package com.nicat.suman.dao.repository;

import com.nicat.suman.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.font.OpenType;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);

}