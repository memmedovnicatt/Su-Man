package com.nicat.suman.dao.repository;

import com.nicat.suman.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE " +
            "c.name LIKE CONCAT('%', :name, '%') OR " +
            "c.surname LIKE CONCAT('%', :surname, '%')")
    Optional<List<Customer>> findByNameAndSurname(@Param("name") String name,
                                                  @Param("surname") String surname);

    @Query("SELECT c FROM Customer c WHERE " +
            "c.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')")
    Optional<List<Customer>> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}